package com.ebgolden.domain.turnqueueservice.dal;

import com.ebgolden.domain.turnqueueservice.dal.dao.EncounterDao;
import com.ebgolden.domain.turnqueueservice.dal.dao.TurnQueueDao;

public interface TurnQueueDataAccess {
    TurnQueueDao getTurnQueueDao(EncounterDao encounterDao);
}