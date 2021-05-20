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

    public ResultBo getResultBo(ActionAndDiceRollsAndCharacterAndPlayerBo actionAndDiceRollsAndCharacterAndPlayerBo) {
        ActionAndDiceRollsAndCharacterAndPlayerBo filteredActionAndDiceRollsAndCharacterAndPlayerBo = filterActionAndDiceRollsAndCharacterAndPlayerBo(actionAndDiceRollsAndCharacterAndPlayerBo);
        ActionAndDiceRollsAndCharacterDao actionAndDiceRollsAndCharacterDao = actionDataAccessConverter.getActionAndDiceRollsAndCharacterDaoFromActionAndDiceRollsBo(filteredActionAndDiceRollsAndCharacterAndPlayerBo);
        ResultDao resultDao = actionDataAccess.getResultDao(actionAndDiceRollsAndCharacterDao);
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

    private ActionAndDiceRollsAndCharacterAndPlayerBo filterActionAndDiceRollsAndCharacterAndPlayerBo(ActionAndDiceRollsAndCharacterAndPlayerBo actionAndDiceRollsAndCharacterAndPlayerBo) {
        Action action = actionAndDiceRollsAndCharacterAndPlayerBo.getAction();
        int[] diceRolls = actionAndDiceRollsAndCharacterAndPlayerBo.getDiceRolls();
        Character character = actionAndDiceRollsAndCharacterAndPlayerBo.getCharacter();
        Player player = actionAndDiceRollsAndCharacterAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        if (!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class)) {
            action = null;
            diceRolls = new int[] {};
            character = null;
        }
        return ActionAndDiceRollsAndCharacterAndPlayerBo
                .builder()
                .action(action)
                .diceRolls(diceRolls)
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