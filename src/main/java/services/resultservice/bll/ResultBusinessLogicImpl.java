package services.resultservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import commonobjects.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.resultservice.bll.bo.ResultAndPlayerBo;
import services.resultservice.bll.bo.ResultAndVisibilityAndPlayerBo;
import services.resultservice.bll.bo.ResultAndVisibilityBo;
import services.resultservice.dal.ResultDataAccess;
import services.resultservice.dal.ResultDataAccessConverter;
import services.resultservice.dal.dao.ResultDao;
import services.resultservice.dal.dao.ResultAndVisibilityDao;
import java.util.Map;

public class ResultBusinessLogicImpl implements ResultBusinessLogic {
    @Inject
    private ResultDataAccessConverter resultDataAccessConverter;
    @Inject
    private ResultDataAccess resultDataAccess;
    @Inject
    private ResultBusinessLogicConverter resultBusinessLogicConverter;

    public ResultAndVisibilityBo getResultAndVisibilityBo(ResultAndPlayerBo resultAndPlayerBo) {
        ResultDao resultDao = resultDataAccessConverter.getResultDaoFromResultAndPlayerBo(resultAndPlayerBo);
        ResultAndVisibilityDao resultAndVisibilityDao = resultDataAccess.getResultAndVisibilityDao(resultDao);
        ResultAndVisibilityBo resultAndVisibilityBo = resultDataAccessConverter.getResultAndVisibilityBoFromResultAndVisibilityDao(resultAndVisibilityDao);
        Player player = resultAndPlayerBo.getPlayer();
        if (resultAndVisibilityBo.getResult() == null)
            return resultAndVisibilityBo;
        return filterResultAndVisibilityBo(resultAndVisibilityBo, player);
    }

    public ResultAndVisibilityBo getResultAndVisibilityBo(ResultAndVisibilityAndPlayerBo resultAndVisibilityAndPlayerBo) {
        ResultAndVisibilityAndPlayerBo filteredResultAndVisibilityAndPlayerBo = filterResultAndVisibilityAndPlayerBo(resultAndVisibilityAndPlayerBo);
        ResultAndVisibilityBo resultAndVisibilityBo = resultBusinessLogicConverter.getResultAndVisibilityBoFromResultAndVisibilityAndPlayerBo(filteredResultAndVisibilityAndPlayerBo);
        ResultAndVisibilityDao resultAndVisibilityDao = resultDataAccessConverter.getResultAndVisibilityDaoFromResultAndVisibilityBo(resultAndVisibilityBo);
        resultAndVisibilityDao = resultDataAccess.getResultAndVisibilityDao(resultAndVisibilityDao);
        return resultDataAccessConverter.getResultAndVisibilityBoFromResultAndVisibilityDao(resultAndVisibilityDao);
    }

    private ResultAndVisibilityBo filterResultAndVisibilityBo(ResultAndVisibilityBo resultAndVisibilityBo, Player player) {
        Result result = resultAndVisibilityBo.getResult();
        Map<String, Visibility> visibilityMap = resultAndVisibilityBo.getVisibilityMap();
        String playerId = player.getId();
        String resultPlayerId = result.getPlayerId();
        Result filteredResult = result;
        if (player.getClass() != DungeonMaster.class) {
            ObjectMapper objectMapper = new ObjectMapper();
            String resultJson = "{}";
            String visibilityJson = "{}";
            try {
                resultJson = objectMapper.writeValueAsString(result);
                visibilityJson = objectMapper.writeValueAsString(visibilityMap);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject resultJsonObject = new JSONObject();
            JSONObject visibilityJsonObject = new JSONObject();
            try {
                resultJsonObject = (JSONObject)jsonParser.parse(resultJson);
                visibilityJsonObject = (JSONObject)jsonParser.parse(visibilityJson);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String[] visibilityKeys = new String[] {};
            visibilityKeys = (String[])visibilityJsonObject
                    .keySet()
                    .toArray(visibilityKeys);
            for (String visibilityKey : visibilityKeys) {
                Visibility visibility = Visibility.valueOf(visibilityJsonObject.get(visibilityKey).toString().toUpperCase());
                if (resultJsonObject.containsKey(visibilityKey) &&
                        (((!playerId.equals(resultPlayerId)) && (visibility != Visibility.EVERYONE)) ||
                                ((playerId.equals(resultPlayerId)) && (visibility == Visibility.DUNGEON_MASTER))))
                    resultJsonObject.remove(visibilityKey);
            }
            JSONObject filteredResultJsonObject = resultJsonObject;
            String filteredResultJson = filteredResultJsonObject.toJSONString();
            try {
                filteredResult = objectMapper.readValue(filteredResultJson, Result.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ResultAndVisibilityBo
                .builder()
                .result(filteredResult)
                .visibilityMap(visibilityMap)
                .build();
    }

    private ResultAndVisibilityAndPlayerBo filterResultAndVisibilityAndPlayerBo(ResultAndVisibilityAndPlayerBo resultAndVisibilityAndPlayerBo) {
        Result result = resultAndVisibilityAndPlayerBo.getResult();
        Map<String, Visibility> visibilityMap = resultAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = resultAndVisibilityAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String resultPlayerId = result.getPlayerId();
        if (!playerId.equals(resultPlayerId) && (player.getClass() != DungeonMaster.class)) {
            result = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null) {
            Result finalResult = result;
            visibilityMap.keySet().forEach(key -> correctVisibility(player, resultPlayerId, filteredVisibilityMap, key, finalResult));
        }
        return ResultAndVisibilityAndPlayerBo
                .builder()
                .result(result)
                .visibilityMap(filteredVisibilityMap)
                .player(player)
                .build();
    }

    private void correctVisibility(Player player, String resultPlayerId, Map<String, Visibility> visibilityMap, String key, Result result) {
        String playerId = player.getId();
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = "{}";
        try {
            resultJson = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject resultJsonObject = new JSONObject();
        try {
            resultJsonObject = (JSONObject)jsonParser.parse(resultJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!resultJsonObject.containsKey(key))
            visibilityMap.remove(key);
        else if ((player.getClass() != DungeonMaster.class) && (visibilityMap.get(key) == Visibility.DUNGEON_MASTER))
            visibilityMap.replace(key, Visibility.PLAYER);
        else if (playerId.equals(resultPlayerId) && (player.getClass() == DungeonMaster.class) && (visibilityMap.get(key) == Visibility.PLAYER))
            visibilityMap.replace(key, Visibility.DUNGEON_MASTER);
    }
}