package services.actiondetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Action;
import objects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.actiondetailservice.bll.bo.ActionAndPlayerBo;
import services.actiondetailservice.bll.bo.ActionDetailsAndVisibilityBo;
import services.actiondetailservice.dal.dao.ActionDao;
import services.actiondetailservice.dal.dao.ActionDetailsAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class ActionDetailDataAccessConverterImpl implements ActionDetailDataAccessConverter {
    public ActionDao getActionDaoFromActionAndPlayerBo(ActionAndPlayerBo actionAndPlayerBo) {
        Action action = actionAndPlayerBo.getAction();
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson = "{}";
        try {
            actionJson = objectMapper.writeValueAsString(action);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ActionDao
                .builder()
                .actionJson(actionJson)
                .build();
    }

    public ActionDetailsAndVisibilityDao getActionDetailsAndVisibilityDaoFromActionDetailsAndVisibilityBo(ActionDetailsAndVisibilityBo actionDetailsAndVisibilityBo) {
        Action action = actionDetailsAndVisibilityBo.getAction();
        Map<String, Visibility> visibilityMap = actionDetailsAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson = "{}";
        String visibilityJson = "{}";
        try {
            actionJson = objectMapper.writeValueAsString(action);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String actionDetailsAndVisibilityJson = "{}";
        if ((!actionJson.equals("{}") && (!actionJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            actionDetailsAndVisibilityJson = "{" +
                    "actionDetails:" +
                    actionJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return ActionDetailsAndVisibilityDao
                .builder()
                .actionDetailsAndVisibilityJson(actionDetailsAndVisibilityJson)
                .build();
    }

    public ActionDetailsAndVisibilityBo getActionDetailsAndVisibilityBoFromActionDetailsAndVisibilityDao(ActionDetailsAndVisibilityDao actionDetailsAndVisibilityDao) {
        String actionDetailsAndVisibilityJson = actionDetailsAndVisibilityDao.getActionDetailsAndVisibilityJson();
        if (actionDetailsAndVisibilityJson == null)
            actionDetailsAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(actionDetailsAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String actionDetailsJson;
        Action action = null;
        if (jsonObject.get("actionDetails") != null) {
            actionDetailsJson = ((JSONObject) jsonObject.get("actionDetails")).toJSONString();
            action = Action
                    .builder()
                    .build();
            try {
                action = objectMapper.readValue(actionDetailsJson, Action.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        String visibilityJson;
        Map<String, Visibility> visibilityMap = null;
        if (jsonObject.get("visibility") != null) {
            visibilityJson = ((JSONObject)jsonObject.get("visibility")).toJSONString();
            visibilityMap = new HashMap<>();
            try {
                visibilityMap = objectMapper.readValue(visibilityJson, Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ActionDetailsAndVisibilityBo
                .builder()
                .action(action)
                .visibilityMap(visibilityMap)
                .build();
    }

    public ActionDetailsAndVisibilityDao getActionDetailsAndVisibilityDaoFromActionDetailsAndVisibilityJson(String actionDetailsAndVisibilityJson) {
        return ActionDetailsAndVisibilityDao
                .builder()
                .actionDetailsAndVisibilityJson(actionDetailsAndVisibilityJson)
                .build();
    }
}