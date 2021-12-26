package com.ebgolden.domain.turnqueueservice.bll;

import com.ebgolden.domain.turnqueueservice.TurnQueueRequest;
import com.ebgolden.domain.turnqueueservice.TurnQueueResponse;
import com.ebgolden.domain.turnqueueservice.bll.bo.EncounterBo;
import com.ebgolden.domain.turnqueueservice.bll.bo.TurnQueueBo;

public interface TurnQueueBusinessLogicConverter {
    EncounterBo getEncounterBoFromTurnQueueRequest(TurnQueueRequest turnQueueRequest);

    TurnQueueResponse getTurnQueueResponseFromTurnQueueBo(TurnQueueBo turnQueueBo);
}