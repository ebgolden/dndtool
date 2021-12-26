package com.ebgolden.domain.actionservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.ebgolden.common.*;
import com.ebgolden.common.Character;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.ebgolden.domain.actionservice.bll.bo.*;
import com.ebgolden.domain.actionservice.dal.ActionDataAccess;
import com.ebgolden.domain.actionservice.dal.ActionDataAccessConverter;
import com.ebgolden.domain.actionservice.dal.dao.*;
import java.util.Map;

public class ActionBusinessLogicImpl implements ActionBusinessLogic {
    @Inject
    private ActionDataAccessConverter actionDataAccessConverter;
    @Inject
    private ActionDataAccess actionDataAccess;
    @Inject
    private ActionBusinessLogicConverter actionBusinessLogicConverter;

    public ActionsBo getActionsBo(CharacterAndPlayerBo characterAndPlayerBo) {
        CharacterAndPlayerBo filteredCharacterAndPlayerBo = filterCharacterAndPlayerBo(characterAndPlayerBo);
        CharacterDao characterDao = actionDataAccessConverter.getCharacterDaoFromCharacterAndPlayerBo(filteredCharacterAndPlayerBo);
        ActionsDao actionsDao = actionDataAccess.getActionsDao(characterDao);
        return actionDataAccessConverter.getActionsBoFromActionsDao(actionsDao);
    }

    public ResultBo getResultBo(ActionAndDiceAndCharacterAndPlayerBo actionAndDiceAndCharacterAndPlayerBo) {
        ActionAndDiceAndCharacterAndPlayerBo filteredActionAndDiceAndCharacterAndPlayerBo = filterActionAndDiceAndCharacterAndPlayerBo(actionAndDiceAndCharacterAndPlayerBo);
        ActionAndDiceAndCharacterDao actionAndDiceAndCharacterDao = actionDataAccessConverter.getActionAndDiceAndCharacterDaoFromActionAndDiceAndCharacterAndPlayerBo(filteredActionAndDiceAndCharacterAndPlayerBo);
        ResultDao resultDao = actionDataAccess.getResultDao(actionAndDiceAndCharacterDao);
        return actionDataAccessConverter.getResultBoFromResultDao(resultDao);
    }

    public ActionBo getActionBo(NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo) {
        NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo filteredNonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo = filterNonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo(nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo);
        NonStandardActionAndCharacterDao nonStandardActionAndCharacterDao = actionDataAccessConverter.getNonStandardActionAndCharacterDaoFromNonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo(filteredNonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo);
        ActionDao actionDao = actionDataAccess.getActionDao(nonStandardActionAndCharacterDao);
        return actionDataAccessConverter.getActionBoFromActionDao(actionDao);
    }

    public ActionAndVisibilityBo getActionAndVisibilityBo(ActionAndPlayerBo actionAndPlayerBo) {
        ActionDao actionDao = actionDataAccessConverter.getActionDaoFromActionAndPlayerBo(actionAndPlayerBo);
        ActionAndVisibilityDao actionAndVisibilityDao = actionDataAccess.getActionAndVisibilityDao(actionDao);
        ActionAndVisibilityBo actionAndVisibilityBo = actionDataAccessConverter.getActionAndVisibilityBoFromActionAndVisibilityDao(actionAndVisibilityDao);
        Player player = actionAndPlayerBo.getPlayer();
        if (actionAndVisibilityBo.getAction() == null)
            return actionAndVisibilityBo;
        return filterActionAndVisibilityBo(actionAndVisibilityBo, player);
    }

    public ActionAndVisibilityBo getActionAndVisibilityBo(ActionAndVisibilityAndPlayerBo actionAndVisibilityAndPlayerBo) {
        ActionAndVisibilityAndPlayerBo filteredActionAndVisibilityAndPlayerBo = filterActionAndVisibilityAndPlayerBo(actionAndVisibilityAndPlayerBo);
        ActionAndVisibilityBo actionAndVisibilityBo = actionBusinessLogicConverter.getActionAndVisibilityBoFromActionAndVisibilityAndPlayerBo(filteredActionAndVisibilityAndPlayerBo);
        ActionAndVisibilityDao actionAndVisibilityDao = actionDataAccessConverter.getActionAndVisibilityDaoFromActionAndVisibilityBo(actionAndVisibilityBo);
        actionAndVisibilityDao = actionDataAccess.getActionAndVisibilityDao(actionAndVisibilityDao);
        return actionDataAccessConverter.getActionAndVisibilityBoFromActionAndVisibilityDao(actionAndVisibilityDao);
    }

    private CharacterAndPlayerBo filterCharacterAndPlayerBo(CharacterAndPlayerBo characterAndPlayerBo) {
        Character character = characterAndPlayerBo.getCharacter();
        Player player = characterAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        if (!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class))
            character = null;
        return CharacterAndPlayerBo
                .builder()
                .character(character)
                .player(player)
                .build();
    }

    private ActionAndDiceAndCharacterAndPlayerBo filterActionAndDiceAndCharacterAndPlayerBo(ActionAndDiceAndCharacterAndPlayerBo actionAndDiceAndCharacterAndPlayerBo) {
        Action action = actionAndDiceAndCharacterAndPlayerBo.getAction();
        Die[] dice = actionAndDiceAndCharacterAndPlayerBo.getDice();
        Character character = actionAndDiceAndCharacterAndPlayerBo.getCharacter();
        Player player = actionAndDiceAndCharacterAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        if (!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class)) {
            action = null;
            dice = new Die[] {};
            character = null;
        }
        return ActionAndDiceAndCharacterAndPlayerBo
                .builder()
                .action(action)
                .dice(dice)
                .character(character)
                .player(player)
                .build();
    }

    private NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo filterNonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo(NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo) {
        NonStandardAction nonStandardAction = nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo.getNonStandardAction();
        Character character = nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo.getCharacter();
        Player player = nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo.getPlayer();
        boolean acceptedByDungeonMaster = nonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo.isAcceptedByDungeonMaster();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        if ((!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class)) || !acceptedByDungeonMaster) {
            nonStandardAction = null;
            character = null;
        }
        return NonStandardActionAndCharacterAndPlayerAndAcceptedByDungeonMasterBo
                .builder()
                .nonStandardAction(nonStandardAction)
                .character(character)
                .player(player)
                .build();
    }

    private ActionAndVisibilityBo filterActionAndVisibilityBo(ActionAndVisibilityBo actionAndVisibilityBo, Player player) {
        Action action = actionAndVisibilityBo.getAction();
        Map<String, Visibility> visibilityMap = actionAndVisibilityBo.getVisibilityMap();
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
        return ActionAndVisibilityBo
                .builder()
                .action(filteredAction)
                .visibilityMap(visibilityMap)
                .build();
    }

    private ActionAndVisibilityAndPlayerBo filterActionAndVisibilityAndPlayerBo(ActionAndVisibilityAndPlayerBo actionAndVisibilityAndPlayerBo) {
        Action action = actionAndVisibilityAndPlayerBo.getAction();
        Map<String, Visibility> visibilityMap = actionAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = actionAndVisibilityAndPlayerBo.getPlayer();
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
        return ActionAndVisibilityAndPlayerBo
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