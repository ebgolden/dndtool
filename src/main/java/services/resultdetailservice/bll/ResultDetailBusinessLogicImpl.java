package services.resultdetailservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import objects.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.resultdetailservice.bll.bo.ResultAndPlayerBo;
import services.resultdetailservice.bll.bo.ResultDetailsAndVisibilityAndPlayerBo;
import services.resultdetailservice.bll.bo.ResultDetailsAndVisibilityBo;
import services.resultdetailservice.dal.ResultDetailDataAccess;
import services.resultdetailservice.dal.ResultDetailDataAccessConverter;
import services.resultdetailservice.dal.dao.ResultDao;
import services.resultdetailservice.dal.dao.ResultDetailsAndVisibilityDao;
import java.util.Map;

public class ResultDetailBusinessLogicImpl implements ResultDetailBusinessLogic {
    @Inject
    private ResultDetailDataAccessConverter resultDetailDataAccessConverter;
    @Inject
    private ResultDetailDataAccess resultDetailDataAccess;
    @Inject
    private ResultDetailBusinessLogicConverter resultDetailBusinessLogicConverter;

    public ResultDetailsAndVisibilityBo getResultDetailsAndVisibilityBo(ResultAndPlayerBo resultAndPlayerBo) {
        ResultDao resultDao = resultDetailDataAccessConverter.getResultDaoFromResultAndPlayerBo(resultAndPlayerBo);
        ResultDetailsAndVisibilityDao resultDetailsAndVisibilityDao = resultDetailDataAccess.getResultDetailsAndVisibilityDao(resultDao);
        ResultDetailsAndVisibilityBo resultDetailsAndVisibilityBo = resultDetailDataAccessConverter.getResultDetailsAndVisibilityBoFromResultDetailsAndVisibilityDao(resultDetailsAndVisibilityDao);
        Player player = resultAndPlayerBo.getPlayer();
        if (resultDetailsAndVisibilityBo.getResult() == null)
            return resultDetailsAndVisibilityBo;
        return filterResultDetailsAndVisibilityBo(resultDetailsAndVisibilityBo, player);
    }

    public ResultDetailsAndVisibilityBo getResultDetailsAndVisibilityBo(ResultDetailsAndVisibilityAndPlayerBo resultDetailsAndVisibilityAndPlayerBo) {
        ResultDetailsAndVisibilityAndPlayerBo filteredResultDetailsAndVisibilityAndPlayerBo = filterResultDetailsAndVisibilityAndPlayerBo(resultDetailsAndVisibilityAndPlayerBo);
        ResultDetailsAndVisibilityBo resultDetailsAndVisibilityBo = resultDetailBusinessLogicConverter.getResultDetailsAndVisibilityBoFromResultDetailsAndVisibilityAndPlayerBo(filteredResultDetailsAndVisibilityAndPlayerBo);
        ResultDetailsAndVisibilityDao resultDetailsAndVisibilityDao = resultDetailDataAccessConverter.getResultDetailsAndVisibilityDaoFromResultDetailsAndVisibilityBo(resultDetailsAndVisibilityBo);
        resultDetailsAndVisibilityDao = resultDetailDataAccess.getResultDetailsAndVisibilityDao(resultDetailsAndVisibilityDao);
        return resultDetailDataAccessConverter.getResultDetailsAndVisibilityBoFromResultDetailsAndVisibilityDao(resultDetailsAndVisibilityDao);
    }

    private ResultDetailsAndVisibilityBo filterResultDetailsAndVisibilityBo(ResultDetailsAndVisibilityBo resultDetailsAndVisibilityBo, Player player) {
        Result result = resultDetailsAndVisibilityBo.getResult();
        Map<String, Visibility> visibilityMap = resultDetailsAndVisibilityBo.getVisibilityMap();
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
        return ResultDetailsAndVisibilityBo
                .builder()
                .result(filteredResult)
                .visibilityMap(visibilityMap)
                .build();
    }

    private ResultDetailsAndVisibilityAndPlayerBo filterResultDetailsAndVisibilityAndPlayerBo(ResultDetailsAndVisibilityAndPlayerBo resultDetailsAndVisibilityAndPlayerBo) {
        Result result = resultDetailsAndVisibilityAndPlayerBo.getResult();
        Map<String, Visibility> visibilityMap = resultDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = resultDetailsAndVisibilityAndPlayerBo.getPlayer();
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
        return ResultDetailsAndVisibilityAndPlayerBo
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