package services.actionservice.bll;

import com.google.inject.Inject;
import objects.Character;
import objects.DungeonMaster;
import objects.NonStandardAction;
import objects.Player;
import services.actionservice.bll.bo.*;
import services.actionservice.dal.ActionDataAccess;
import services.actionservice.dal.ActionDataAccessConverter;
import services.actionservice.dal.dao.*;

public class ActionBusinessLogicImpl implements ActionBusinessLogic {
    @Inject
    private ActionDataAccessConverter actionDataAccessConverter;
    @Inject
    private ActionDataAccess actionDataAccess;

    public ActionsBo getActionsBo(CharacterBo characterBo) {
        CharacterDao characterDao = actionDataAccessConverter.getCharacterDaoFromCharacterBo(characterBo);
        ActionsDao actionsDao = actionDataAccess.getActionsDao(characterDao);
        return actionDataAccessConverter.getActionsBoFromActionsDao(actionsDao);
    }

    public ResultBo getResultBo(ActionAndDiceRollsBo actionAndDiceRollsBo) {
        ActionAndDiceRollsDao actionAndDiceRollsDao = actionDataAccessConverter.getActionAndDiceRollsDaoFromActionAndDiceRollsBo(actionAndDiceRollsBo);
        ResultDao resultDao = actionDataAccess.getResultDao(actionAndDiceRollsDao);
        return actionDataAccessConverter.getResultBoFromResultDao(resultDao);
    }

    public ActionBo getActionBo(NonStandardActionAndCharacterAndPlayerBo nonStandardActionAndCharacterAndPlayerBo) {
        NonStandardActionAndCharacterAndPlayerBo filteredNonStandardActionAndCharacterAndPlayerBo = filterNonStandardActionAndCharacterAndPlayerBo(nonStandardActionAndCharacterAndPlayerBo);
        NonStandardActionAndCharacterDao nonStandardActionAndCharacterDao = actionDataAccessConverter.getNonStandardActionAndCharacterDaoFromNonStandardActionAndCharacterAndPlayerBo(filteredNonStandardActionAndCharacterAndPlayerBo);
        ActionDao actionDao = actionDataAccess.getActionDao(nonStandardActionAndCharacterDao);
        return actionDataAccessConverter.getActionBoFromActionDao(actionDao);
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