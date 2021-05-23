package services.actiondetailservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import objects.Action;
import objects.DungeonMaster;
import objects.Player;
import objects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.actiondetailservice.bll.bo.ActionAndPlayerBo;
import services.actiondetailservice.bll.bo.ActionDetailsAndVisibilityAndPlayerBo;
import services.actiondetailservice.bll.bo.ActionDetailsAndVisibilityBo;
import services.actiondetailservice.dal.ActionDetailDataAccess;
import services.actiondetailservice.dal.ActionDetailDataAccessConverter;
import services.actiondetailservice.dal.dao.ActionDao;
import services.actiondetailservice.dal.dao.ActionDetailsAndVisibilityDao;
import java.util.Map;

public class ActionDetailBusinessLogicImpl implements ActionDetailBusinessLogic {
    @Inject
    private ActionDetailDataAccessConverter actionDetailDataAccessConverter;
    @Inject
    private ActionDetailDataAccess actionDetailDataAccess;
    @Inject
    private ActionDetailBusinessLogicConverter actionDetailBusinessLogicConverter;

    public ActionDetailsAndVisibilityBo getActionDetailsAndVisibilityBo(ActionAndPlayerBo actionAndPlayerBo) {
        ActionDao actionDao = actionDetailDataAccessConverter.getActionDaoFromActionAndPlayerBo(actionAndPlayerBo);
        ActionDetailsAndVisibilityDao actionDetailsAndVisibilityDao = actionDetailDataAccess.getActionDetailsAndVisibilityDao(actionDao);
        ActionDetailsAndVisibilityBo actionDetailsAndVisibilityBo = actionDetailDataAccessConverter.getActionDetailsAndVisibilityBoFromActionDetailsAndVisibilityDao(actionDetailsAndVisibilityDao);
        Player player = actionAndPlayerBo.getPlayer();
        if (actionDetailsAndVisibilityBo.getAction() == null)
            return actionDetailsAndVisibilityBo;
        return filterActionDetailsAndVisibilityBo(actionDetailsAndVisibilityBo, player);
    }

    public ActionDetailsAndVisibilityBo getActionDetailsAndVisibilityBo(ActionDetailsAndVisibilityAndPlayerBo actionDetailsAndVisibilityAndPlayerBo) {
        ActionDetailsAndVisibilityAndPlayerBo filteredActionDetailsAndVisibilityAndPlayerBo = filterActionDetailsAndVisibilityAndPlayerBo(actionDetailsAndVisibilityAndPlayerBo);
        ActionDetailsAndVisibilityBo actionDetailsAndVisibilityBo = actionDetailBusinessLogicConverter.getActionDetailsAndVisibilityBoFromActionDetailsAndVisibilityAndPlayerBo(filteredActionDetailsAndVisibilityAndPlayerBo);
        ActionDetailsAndVisibilityDao actionDetailsAndVisibilityDao = actionDetailDataAccessConverter.getActionDetailsAndVisibilityDaoFromActionDetailsAndVisibilityBo(actionDetailsAndVisibilityBo);
        actionDetailsAndVisibilityDao = actionDetailDataAccess.getActionDetailsAndVisibilityDao(actionDetailsAndVisibilityDao);
        return actionDetailDataAccessConverter.getActionDetailsAndVisibilityBoFromActionDetailsAndVisibilityDao(actionDetailsAndVisibilityDao);
    }

    private ActionDetailsAndVisibilityBo filterActionDetailsAndVisibilityBo(ActionDetailsAndVisibilityBo actionDetailsAndVisibilityBo, Player player) {
        Action action = actionDetailsAndVisibilityBo.getAction();
        Map<String, Visibility> visibilityMap = actionDetailsAndVisibilityBo.getVisibilityMap();
        String playerId = player.getId();
        String actionPlayerId = action.getPlayerId();
        Action filteredAction = action;
        if (player.getClass() != DungeonMaster.class) {
            ObjectMapper objectMapper = new ObjectMapper();
            String actionJson = "{}";
            String visibilityJson = "{}";
            try {
                actionJson = objectMapper.writeValueAsString(action);
                visibilityJson = objectMapper.writeValueAsString(visibilityMap);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject actionJsonObject = new JSONObject();
            JSONObject visibilityJsonObject = new JSONObject();
            try {
                actionJsonObject = (JSONObject)jsonParser.parse(actionJson);
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
                if (actionJsonObject.containsKey(visibilityKey) &&
                        (((!playerId.equals(actionPlayerId)) && (visibility != Visibility.EVERYONE)) ||
                                ((playerId.equals(actionPlayerId)) && (visibility == Visibility.DUNGEON_MASTER))))
                    actionJsonObject.remove(visibilityKey);
            }
            JSONObject filteredActionJsonObject = actionJsonObject;
            String filteredActionJson = filteredActionJsonObject.toJSONString();
            try {
                filteredAction = objectMapper.readValue(filteredActionJson, Action.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ActionDetailsAndVisibilityBo
                .builder()
                .action(filteredAction)
                .visibilityMap(visibilityMap)
                .build();
    }

    private ActionDetailsAndVisibilityAndPlayerBo filterActionDetailsAndVisibilityAndPlayerBo(ActionDetailsAndVisibilityAndPlayerBo actionDetailsAndVisibilityAndPlayerBo) {
        Action action = actionDetailsAndVisibilityAndPlayerBo.getAction();
        Map<String, Visibility> visibilityMap = actionDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = actionDetailsAndVisibilityAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String actionPlayerId = action.getPlayerId();
        if (!playerId.equals(actionPlayerId) && (player.getClass() != DungeonMaster.class)) {
            action = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null) {
            Action finalAction = action;
            visibilityMap.keySet().forEach(key -> correctVisibility(player, actionPlayerId, filteredVisibilityMap, key, finalAction));
        }
        return ActionDetailsAndVisibilityAndPlayerBo
                .builder()
                .action(action)
                .visibilityMap(filteredVisibilityMap)
                .player(player)
                .build();
    }

    private void correctVisibility(Player player, String actionPlayerId, Map<String, Visibility> visibilityMap, String key, Action action) {
        String playerId = player.getId();
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson = "{}";
        try {
            actionJson = objectMapper.writeValueAsString(action);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject actionJsonObject = new JSONObject();
        try {
            actionJsonObject = (JSONObject)jsonParser.parse(actionJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!actionJsonObject.containsKey(key))
            visibilityMap.remove(key);
        else if ((player.getClass() != DungeonMaster.class) && (visibilityMap.get(key) == Visibility.DUNGEON_MASTER))
            visibilityMap.replace(key, Visibility.PLAYER);
        else if (playerId.equals(actionPlayerId) && (player.getClass() == DungeonMaster.class) && (visibilityMap.get(key) == Visibility.PLAYER))
            visibilityMap.replace(key, Visibility.DUNGEON_MASTER);
    }
}