package services.actionservice.bll;

import objects.Action;
import objects.Character;
import services.actionservice.ActionsRequest;
import services.actionservice.ActionsResponse;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;

public class ActionBusinessLogicConverterImpl implements ActionBusinessLogicConverter {
    public CharacterBo getCharacterBoFromActionsRequest(ActionsRequest actionsRequest) {
        Character character = actionsRequest.getCharacter();
        return CharacterBo
                .builder()
                .character(character)
                .build();
    }

    public ActionsResponse getActionsResponseFromActionsBo(ActionsBo actionsBo) {
        Action[] actions = actionsBo.getActions();
        return ActionsResponse
                .builder()
                .actions(actions)
                .build();
    }
}