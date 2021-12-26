package domain.turnqueueservice.dal;

import domain.turnqueueservice.bll.bo.EncounterBo;
import domain.turnqueueservice.bll.bo.TurnQueueBo;
import domain.turnqueueservice.dal.dao.EncounterDao;
import domain.turnqueueservice.dal.dao.TurnQueueDao;

public interface TurnQueueDataAccessConverter {
    EncounterDao getEncounterDaoFromEncounterBo(EncounterBo encounterBo);

    TurnQueueBo getTurnQueueBoFromTurnQueueDao(TurnQueueDao turnQueueDao);

    TurnQueueDao getTurnQueueDaoFromTurnQueueJson(String turnQueueJson);
}