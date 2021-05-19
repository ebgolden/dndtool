package services.actionservice.dal;

import services.actionservice.bll.bo.ActionAndDiceRollsBo;
import services.actionservice.bll.bo.ActionsBo;
import services.actionservice.bll.bo.CharacterBo;
import services.actionservice.bll.bo.ResultBo;
import services.actionservice.dal.dao.ActionAndDiceRollsDao;
import services.actionservice.dal.dao.ActionsDao;
import services.actionservice.dal.dao.CharacterDao;
import services.actionservice.dal.dao.ResultDao;

public interface ActionDataAccessConverter {
    CharacterDao getCharacterDaoFromCharacterBo(CharacterBo characterBo);

    ActionAndDiceRollsDao getActionAndDiceRollsDaoFromActionAndDiceRollsBo(ActionAndDiceRollsBo actionAndDiceRollsBo);

    ActionsBo getActionsBoFromActionsDao(ActionsDao actionsDao);

    ResultBo getResultBoFromResultDao(ResultDao resultDao);

    ActionsDao getActionsDaoFromLatestObjectJson(String latestObjectJson);

    ResultDao getResultDaoFromResultObjectJson(String resultObjectJson);
}