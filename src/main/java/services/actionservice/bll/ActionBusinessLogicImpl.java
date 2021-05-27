package services.actionservice.bll;

import com.google.inject.Inject;
import objects.*;
import objects.Character;
import services.actionservice.bll.bo.*;
import services.actionservice.dal.ActionDataAccess;
import services.actionservice.dal.ActionDataAccessConverter;
import services.actionservice.dal.dao.*;

public class ActionBusinessLogicImpl implements ActionBusinessLogic {
    @Inject
    private ActionDataAccessConverter actionDataAccessConverter;
    @Inject
    private ActionDataAccess actionDataAccess;

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

    public ActionBo getActionBo(NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo) {
        NonStandardActionAndCharacterAndPlayerBo filteredNonStandardActionAndCharacterAndPlayerBo = filterNonStandardActionAndCharacterAndPlayerBo(nonStandardActionAndCharacterAndPlayerBo);
        NonStandardActionAndCharacterDao nonStandardActionAndCharacterDao = actionDataAccessConverter.getNonStandardActionAndCharacterDaoFromNonStandardActionAndCharacterAndPlayerBo(filteredNonStandardActionAndCharacterAndPlayerBo);
        ActionDao actionDao = actionDataAccess.getActionDao(nonStandardActionAndCharacterDao);
        return actionDataAccessConverter.getActionBoFromActionDao(actionDao);
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

    private NonStandardActionAndCharacterAndPlayerBo filterNonStandardActionAndCharacterAndPlayerBo(NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo) {
        NonStandardAction nonStandardAction = nonStandardActionAndCharacterAndPlayerBo.getNonStandardAction();
        Character character = nonStandardActionAndCharacterAndPlayerBo.getCharacter();
        Player player = nonStandardActionAndCharacterAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        if (!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class)) {
            nonStandardAction = null;
            character = null;
        }
        return NonStandardActionAndCharacterAndPlayerBo
                .builder()
                .nonStandardAction(nonStandardAction)
                .character(character)
                .player(player)
                .build();
    }
}