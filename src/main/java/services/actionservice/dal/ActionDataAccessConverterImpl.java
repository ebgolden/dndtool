package services.actionservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.*;
import objects.Character;
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