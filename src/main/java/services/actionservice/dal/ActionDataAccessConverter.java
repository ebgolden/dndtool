package services.actionservice.dal;

import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;
import services.actionservice.dal.dao.ActionsDao;
import services.actionservice.dal.dao.CharacterDao;

public interface ActionDataAccessConverter {
    CharacterDao getCharacterDaoFromCharacterBo(CharacterBo characterBo);

    ActionsBo getActionsBoFromActionsDao(ActionsDao actionsDao);

    ActionsDao getActionsDaoFromLatestObjectJson(String latestObjectJson);
}