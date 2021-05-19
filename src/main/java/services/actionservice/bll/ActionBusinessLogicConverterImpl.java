package services.actionservice.bll;

import objects.Action;
import objects.Character;
import objects.Result;
import services.actionservice.ActionsRequest;
import services.actionservice.ActionsResponse;
import services.actionservice.TakeActionRequest;
import services.actionservice.TakeActionResponse;
import services.actionservice.bll.bo.ActionAndDiceRollsBo;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;
import services.actionservice.bll.bo.ResultBo;

public class ActionBusinessLogicConverterImpl implements ActionBusinessLogicConverter {
    public CharacterBo getCharacterBoFromActionsRequest(ActionsRequest actionsRequest) {
        Character character = actionsRequest.getCharacter();
        return CharacterBo
                .builder()
                .character(character)
                .build();
    }

    public ActionAndDiceRollsBo getActionAndDiceRollsBoFromTakeActionRequest(TakeActionRequest takeActionRequest) {
        Action action = takeActionRequest.getAction();
        int[] diceRolls = takeActionRequest.getDiceRolls();
        return ActionAndDiceRollsBo
                .builder()
                .action(action)
                .diceRolls(diceRolls)
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
}