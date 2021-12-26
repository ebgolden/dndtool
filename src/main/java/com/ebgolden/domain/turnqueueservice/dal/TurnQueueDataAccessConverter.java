package com.ebgolden.domain.turnqueueservice.dal;

import com.ebgolden.domain.turnqueueservice.bll.bo.EncounterBo;
import com.ebgolden.domain.turnqueueservice.bll.bo.TurnQueueBo;
import com.ebgolden.domain.turnqueueservice.dal.dao.EncounterDao;
import com.ebgolden.domain.turnqueueservice.dal.dao.TurnQueueDao;

public interface TurnQueueDataAccessConverter {
    EncounterDao getEncounterDaoFromEncounterBo(EncounterBo encounterBo);

    TurnQueueBo getTurnQueueBoFromTurnQueueDao(TurnQueueDao turnQueueDao);

    TurnQueueDao getTurnQueueDaoFromTurnQueueJson(String turnQueueJson);
}