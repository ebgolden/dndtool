package services.actionservice.bll;

import services.actionservice.bll.bo.ActionAndDiceRollsBo;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;
import services.actionservice.bll.bo.ResultBo;

public interface ActionBusinessLogic {
    ActionsBo getActionsBo(CharacterBo characterBo);

    ResultBo getResultBo(ActionAndDiceRollsBo actionAndDiceRollsBo);
}