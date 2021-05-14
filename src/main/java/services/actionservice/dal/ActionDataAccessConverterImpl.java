package services.actionservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Action;
import objects.CharacterObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;
import services.actionservice.dal.dao.ActionsDao;
import services.actionservice.dal.dao.CharacterDao;

public class ActionDataAccessConverterImpl implements ActionDataAccessConverter {
    public CharacterDao getCharacterDaoFromCharacterBo(CharacterBo characterBo) {
        CharacterObject character = characterBo.getCharacter();
        ObjectMapper objectMapper = new ObjectMapper();
        String characterJson = "{}";
        try {
            characterJson = objectMapper.writeValueAsString(character);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return CharacterDao
                .builder()
                .characterJson(characterJson)
                .build();
    }

    public ActionsBo getActionsBoFromActionsDao(ActionsDao actionsDao) {
        String actionsJson = actionsDao.getActionsJson();
        if (actionsJson == null)
            actionsJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(actionsJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = (JSONArray)jsonObject.get("actions");
        int numberOfActions = 0;
        if (jsonArray != null)
            numberOfActions = jsonArray.size();
        Action[] actions = new Action[numberOfActions];
        ObjectMapper objectMapper = new ObjectMapper();
        for (int actionIndex = 0; actionIndex < numberOfActions; ++actionIndex) {
            String actionJson = ((JSONObject)jsonArray.get(actionIndex)).toJSONString();
            try {
                actions[actionIndex] = objectMapper.readValue(actionJson, Action.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ActionsBo
                .builder()
                .actions(actions)
                .build();
    }

    public ActionsDao getActionsDaoFromLatestObjectJson(String latestObjectJson) {
        return ActionsDao
                .builder()
                .actionsJson(latestObjectJson)
                .build();
    }
}