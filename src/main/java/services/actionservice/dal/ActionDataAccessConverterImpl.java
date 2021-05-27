package services.actionservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonobjects.*;
import commonobjects.Character;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.actionservice.bll.bo.*;
import services.actionservice.dal.dao.*;
import java.util.HashMap;
import java.util.Map;

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

    public ActionAndDiceAndCharacterDao getActionAndDiceAndCharacterDaoFromActionAndDiceAndCharacterAndPlayerBo(ActionAndDiceAndCharacterAndPlayerBo actionAndDiceAndCharacterAndPlayerBo) {
        Action action = actionAndDiceAndCharacterAndPlayerBo.getAction();
        Die[] dice = actionAndDiceAndCharacterAndPlayerBo.getDice();
        Character character = actionAndDiceAndCharacterAndPlayerBo.getCharacter();
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson = "{}";
        String diceJson = "{}";
        String characterJson = "{}";
        try {
            actionJson = objectMapper.writeValueAsString(action);
            diceJson = objectMapper.writeValueAsString(dice);
            characterJson = objectMapper.writeValueAsString(character);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String actionAndDiceAndCharacterJson = "{}";
        if (!actionJson.equals("{}") && !actionJson.equals("null") && !diceJson.equals("{}") && !diceJson.equals("null") && !characterJson.equals("{}") && !characterJson.equals("null"))
            actionAndDiceAndCharacterJson = "{" +
                    "\"action\":" +
                    actionJson +
                    ",\"dice\":" +
                    diceJson +
                    ",\"character\":" +
                    characterJson +
                    "}";
        return ActionAndDiceAndCharacterDao
                .builder()
                .actionAndDiceAndCharacterJson(actionAndDiceAndCharacterJson)
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

    public ActionAndVisibilityDao getActionAndVisibilityDaoFromActionAndVisibilityBo(ActionAndVisibilityBo actionAndVisibilityBo) {
        Action action = actionAndVisibilityBo.getAction();
        Map<String, Visibility> visibilityMap = actionAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson = "{}";
        String visibilityJson = "{}";
        try {
            actionJson = objectMapper.writeValueAsString(action);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String actionAndVisibilityJson = "{}";
        if ((!actionJson.equals("{}") && (!actionJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            actionAndVisibilityJson = "{" +
                    "action:" +
                    actionJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return ActionAndVisibilityDao
                .builder()
                .actionAndVisibilityJson(actionAndVisibilityJson)
                .build();
    }

    public ActionsBo getActionsBoFromActionsDao(ActionsDao actionsDao) {
        String actionsJson = actionsDao.getActionsJson();
        if (actionsJson == null)
            actionsJson = "{}";
        ObjectMapper objectMapper = new ObjectMapper();
        Action[] actions;
        try {
            actions = objectMapper.readValue(actionsJson, Action[].class);
        } catch (JsonProcessingException e) {
            actions = new Action[] {};
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

    public ActionAndVisibilityBo getActionAndVisibilityBoFromActionAndVisibilityDao(ActionAndVisibilityDao actionAndVisibilityDao) {
        String actionAndVisibilityJson = actionAndVisibilityDao.getActionAndVisibilityJson();
        if (actionAndVisibilityJson == null)
            actionAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(actionAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String actionJson;
        Action action = null;
        if (jsonObject.get("action") != null) {
            actionJson = ((JSONObject) jsonObject.get("action")).toJSONString();
            action = Action
                    .builder()
                    .build();
            try {
                action = objectMapper.readValue(actionJson, Action.class);
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
                TypeReference<Map<String, Visibility>> visibilityMapTypeReference = new TypeReference<Map<String, Visibility>>(){};
                visibilityMap = objectMapper.readValue(visibilityJson, visibilityMapTypeReference);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ActionAndVisibilityBo
                .builder()
                .action(action)
                .visibilityMap(visibilityMap)
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

    public ActionAndVisibilityDao getActionAndVisibilityDaoFromActionAndVisibilityJson(String actionAndVisibilityJson) {
        return ActionAndVisibilityDao
                .builder()
                .actionAndVisibilityJson(actionAndVisibilityJson)
                .build();
    }
}