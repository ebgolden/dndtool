package services.actionservice.bll;

import objects.Action;
import objects.CharacterObject;
import services.actionservice.ActionsRequest;
import services.actionservice.ActionsResponse;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;

public class ActionBusinessLogicConverterImpl implements ActionBusinessLogicConverter {
    public CharacterBo getCharacterBoFromActionsRequest(ActionsRequest actionsRequest) {
        CharacterObject characterObject = actionsRequest.getCharacter();
        return CharacterBo
                .builder()
                .character(characterObject)
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