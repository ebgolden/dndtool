package com.ebgolden.domain.turnqueueservice;

import com.google.inject.Inject;
import com.ebgolden.domain.turnqueueservice.bll.TurnQueueBusinessLogic;
import com.ebgolden.domain.turnqueueservice.bll.TurnQueueBusinessLogicConverter;
import com.ebgolden.domain.turnqueueservice.bll.bo.EncounterBo;
import com.ebgolden.domain.turnqueueservice.bll.bo.TurnQueueBo;

public class GetTurnQueueImpl implements GetTurnQueue {
    @Inject
    private TurnQueueBusinessLogicConverter turnQueueBusinessLogicConverter;
    @Inject
    private TurnQueueBusinessLogic turnQueueBusinessLogic;

    public TurnQueueResponse getTurnQueueResponse(TurnQueueRequest turnQueueRequest) {
        EncounterBo encounterBo = turnQueueBusinessLogicConverter.getEncounterBoFromTurnQueueRequest(turnQueueRequest);
        TurnQueueBo turnQueueBo = turnQueueBusinessLogic.getTurnQueueBo(encounterBo);
        return turnQueueBusinessLogicConverter.getTurnQueueResponseFromTurnQueueBo(turnQueueBo);
    }
}