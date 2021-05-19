package services.actionservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Action;
import objects.Character;
import objects.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.actionservice.bll.bo.ActionAndDiceRollsBo;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;
import services.actionservice.bll.bo.ResultBo;
import services.actionservice.dal.dao.ActionAndDiceRollsDao;
import services.actionservice.dal.dao.ActionsDao;
import services.actionservice.dal.dao.CharacterDao;
import services.actionservice.dal.dao.ResultDao;

public class ActionDataAccessConverterImpl implements ActionDataAccessConverter {
    public CharacterDao getCharacterDaoFromCharacterBo(CharacterBo characterBo) {
        Character character = characterBo.getCharacter();
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

    public ActionAndDiceRollsDao getActionAndDiceRollsDaoFromActionAndDiceRollsBo(ActionAndDiceRollsBo actionAndDiceRollsBo) {
        Action action = actionAndDiceRollsBo.getAction();
        int[] diceRolls = actionAndDiceRollsBo.getDiceRolls();
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson = "{}";
        String diceRollsJson = "{}";
        try {
            actionJson = objectMapper.writeValueAsString(action);
            diceRollsJson = objectMapper.writeValueAsString(diceRolls);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String actionAndDiceRollsJson = "{}";
        if (!actionJson.equals("{}") && !actionJson.equals("null") && !diceRollsJson.equals("{}") && !diceRollsJson.equals("null"))
            actionAndDiceRollsJson = "{" +
                    "\"action\":" +
                    actionJson +
                    ",\"diceRolls\":" +
                    diceRollsJson +
                    "}";
        return ActionAndDiceRollsDao
                .builder()
                .actionAndDiceRollsJson(actionAndDiceRollsJson)
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

    public ResultBo getResultBoFromResultDao(ResultDao resultDao) {
        String resultJson = resultDao.getResultJson();
        if (resultJson == null)
            resultJson = "{}";
        Result result = null;
        if (!resultJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            result = Result
                    .builder()
                    .build();
            try {
                result = objectMapper.readValue(resultJson, Result.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ResultBo
                .builder()
                .result(result)
                .build();
    }

    public ActionsDao getActionsDaoFromLatestObjectJson(String latestObjectJson) {
        return ActionsDao
                .builder()
                .actionsJson(latestObjectJson)
                .build();
    }

    public ResultDao getResultDaoFromResultObjectJson(String resultObjectJson) {
        return ResultDao
                .builder()
                .resultJson(resultObjectJson)
                .build();
    }
}