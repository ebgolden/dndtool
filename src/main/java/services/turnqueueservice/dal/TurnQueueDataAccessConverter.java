package services.turnqueueservice.dal;

import services.turnqueueservice.bll.bo.EncounterBo;
import services.turnqueueservice.bll.bo.TurnQueueBo;
import services.turnqueueservice.dal.dao.EncounterDao;
import services.turnqueueservice.dal.dao.TurnQueueDao;

public interface TurnQueueDataAccessConverter {
    EncounterDao getEncounterDaoFromEncounterBo(EncounterBo encounterBo);

    TurnQueueBo getTurnQueueBoFromTurnQueueDao(TurnQueueDao turnQueueDao);

    TurnQueueDao getTurnQueueDaoFromLatestObjectJson(String latestObjectJson);
}