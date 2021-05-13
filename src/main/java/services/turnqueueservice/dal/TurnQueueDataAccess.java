package services.turnqueueservice.dal;

import services.turnqueueservice.dal.dao.EncounterDao;
import services.turnqueueservice.dal.dao.TurnQueueDao;

public interface TurnQueueDataAccess {
    TurnQueueDao getTurnQueueDao(EncounterDao encounterDao);
}