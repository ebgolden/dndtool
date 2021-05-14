package services.actionservice.bll;

import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;

public interface ActionBusinessLogic {
    ActionsBo getActionsBo(CharacterBo characterBo);
}