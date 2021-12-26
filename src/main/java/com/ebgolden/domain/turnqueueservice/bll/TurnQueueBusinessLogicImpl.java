package com.ebgolden.domain.turnqueueservice.bll;

import com.google.inject.Inject;
import com.ebgolden.domain.turnqueueservice.bll.bo.EncounterBo;
import com.ebgolden.domain.turnqueueservice.bll.bo.TurnQueueBo;
import com.ebgolden.domain.turnqueueservice.dal.TurnQueueDataAccess;
import com.ebgolden.domain.turnqueueservice.dal.TurnQueueDataAccessConverter;
import com.ebgolden.domain.turnqueueservice.dal.dao.EncounterDao;
import com.ebgolden.domain.turnqueueservice.dal.dao.TurnQueueDao;

public class TurnQueueBusinessLogicImpl implements TurnQueueBusinessLogic {
    @Inject
    private TurnQueueDataAccessConverter turnQueueDataAccessConverter;
    @Inject
    private TurnQueueDataAccess turnQueueDataAccess;

    public TurnQueueBo getTurnQueueBo(EncounterBo encounterBo) {
        EncounterDao encounterDao = turnQueueDataAccessConverter.getEncounterDaoFromEncounterBo(encounterBo);
        TurnQueueDao turnQueueDao = turnQueueDataAccess.getTurnQueueDao(encounterDao);
        return turnQueueDataAccessConverter.getTurnQueueBoFromTurnQueueDao(turnQueueDao);
    }
}