package domain.turnqueueservice.dal;

import domain.turnqueueservice.dal.dao.EncounterDao;
import domain.turnqueueservice.dal.dao.TurnQueueDao;

public interface TurnQueueDataAccess {
    TurnQueueDao getTurnQueueDao(EncounterDao encounterDao);
}