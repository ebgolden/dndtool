package services.actionservice.bll;

import objects.*;
import objects.Character;
import services.actionservice.*;
import services.actionservice.bll.bo.*;

public class ActionBusinessLogicConverterImpl implements ActionBusinessLogicConverter {
    public CharacterBo getCharacterBoFromActionsRequest(ActionsRequest actionsRequest) {
        Character character = actionsRequest.getCharacter();
        return CharacterBo
                .builder()
                .character(character)
                .build();
    }

    public ActionAndDiceRollsAndCharacterAndPlayerBo getActionAndDiceRollsAndCharacterAndPlayerBoFromTakeActionRequest(TakeActionRequest takeActionRequest) {
        Action action = takeActionRequest.getAction();
        int[] diceRolls = takeActionRequest.getDiceRolls();
        Character character = takeActionRequest.getCharacter();
        Player player = takeActionRequest.getPlayer();
        return ActionAndDiceRollsAndCharacterAndPlayerBo
                .builder()
                .action(action)
                .diceRolls(diceRolls)
                .character(character)
                .player(player)
                .build();
    }

    public NonStandardActionAndCharacterAndPlayerBo getNonStandardActionAndCharacterAndPlayerBoFromActionFromNonStandardActionRequest(ActionFromNonStandardActionRequest actionFromNonStandardActionRequest) {
        NonStandardAction nonStandardAction = actionFromNonStandardActionRequest.getNonStandardAction();
        Character character = actionFromNonStandardActionRequest.getCharacter();
        Player player = actionFromNonStandardActionRequest.getPlayer();
        return NonStandardActionAndCharacterAndPlayerBo
                .builder()
                .nonStandardAction(nonStandardAction)
                .character(character)
                .player(player)
                .build();
    }

    public ActionsResponse getActionsResponseFromActionsBo(ActionsBo actionsBo) {
        Action[] actions = actionsBo.getActions();
        return ActionsResponse
                .builder()
                .actions(actions)
                .build();
    }

    public TakeActionResponse getTakeActionResponseFromResultBo(ResultBo resultBo) {
        Result result = resultBo.getResult();
        return TakeActionResponse
                .builder()
                .result(result)
                .build();
    }

    public ActionFromNonStandardActionResponse getActionFromNonStandardActionResponseFromActionBo(ActionBo actionBo) {
        Action action = actionBo.getAction();
        return ActionFromNonStandardActionResponse
                .builder()
                .action(action)
                .build();
    }
}