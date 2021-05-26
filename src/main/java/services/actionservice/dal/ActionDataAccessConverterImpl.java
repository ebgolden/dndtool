package services.actionservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Action;
import objects.Character;
import objects.NonStandardAction;
import objects.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.actionservice.bll.bo.*;
import services.actionservice.dal.dao.*;

public class ActionDataAccessConverterImpl implements ActionDataAccessConverter {
    public CharacterDao getCharacterDaoFromCharacterAndPlayerBo(CharacterAndPlayerBo characterAndPlayerBo) {
        Character character = characterAndPlayerBo.getCharacter();
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

    public ActionAndDiceRollsAndCharacterDao getActionAndDiceRollsAndCharacterDaoFromActionAndDiceRollsBo(ActionAndDiceRollsAndCharacterAndPlayerBo actionAndDiceRollsAndCharacterAndPlayerBo) {
        Action action = actionAndDiceRollsAndCharacterAndPlayerBo.getAction();
        int[] diceRolls = actionAndDiceRollsAndCharacterAndPlayerBo.getDiceRolls();
        Character character = actionAndDiceRollsAndCharacterAndPlayerBo.getCharacter();
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson = "{}";
        String diceRollsJson = "{}";
        String characterJson = "{}";
        try {
            actionJson = objectMapper.writeValueAsString(action);
            diceRollsJson = objectMapper.writeValueAsString(diceRolls);
            characterJson = objectMapper.writeValueAsString(character);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String actionAndDiceRollsAndCharacterJson = "{}";
        if (!actionJson.equals("{}") && !actionJson.equals("null") && !diceRollsJson.equals("{}") && !diceRollsJson.equals("null") && !characterJson.equals("{}") && !characterJson.equals("null"))
            actionAndDiceRollsAndCharacterJson = "{" +
                    "\"action\":" +
                    actionJson +
                    ",\"diceRolls\":" +
                    diceRollsJson +
                    ",\"character\":" +
                    characterJson +
                    "}";
        return ActionAndDiceRollsAndCharacterDao
                .builder()
                .actionAndDiceRollsAndCharacterJson(actionAndDiceRollsAndCharacterJson)
                .build();
    }

    public NonStandardActionAndCharacterDao getNonStandardActionAndCharacterDaoFromNonStandardActionAndCharacterAndPlayerBo(NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo) {
        NonStandardAction nonStandardAction = nonStandardActionAndCharacterAndPlayerBo.getNonStandardAction();
        Character character = nonStandardActionAndCharacterAndPlayerBo.getCharacter();
        ObjectMapper objectMapper = new ObjectMapper();
        String nonStandardActionJson = "{}";
        String characterJson = "{}";
        try {
            nonStandardActionJson = objectMapper.writeValueAsString(nonStandardAction);
            characterJson = objectMapper.writeValueAsString(character);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String nonStandardActionAndCharacterJson = "{}";
        if (!nonStandardActionJson.equals("{}") && !nonStandardActionJson.equals("null") && !characterJson.equals("{}") && !characterJson.equals("null"))
            nonStandardActionAndCharacterJson = "{" +
                    "\"nonStandardAction\":" +
                    nonStandardActionJson +
                    ",\"character\":" +
                    characterJson +
                    "}";
        return NonStandardActionAndCharacterDao
                .builder()
                .nonStandardActionAndCharacterJson(nonStandardActionAndCharacterJson)
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

    public ActionBo getActionBoFromActionDao(ActionDao actionDao) {
        String actionJson = actionDao.getActionJson();
        if (actionJson == null)
            actionJson = "{}";
        Action action = null;
        if (!actionJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            action = Action
                    .builder()
                    .build();
            try {
                action = objectMapper.readValue(actionJson, Action.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ActionBo
                .builder()
                .action(action)
                .build();
    }

    public ActionsDao getActionsDaoFromActionsJson(String actionsJson) {
        return ActionsDao
                .builder()
                .actionsJson(actionsJson)
                .build();
    }

    public ResultDao getResultDaoFromResultJson(String resultJson) {
        return ResultDao
                .builder()
                .resultJson(resultJson)
                .build();
    }

    public ActionDao getActionDaoFromActionJson(String actionJson) {
        return ActionDao
                .builder()
                .actionJson(actionJson)
                .build();
    }
}