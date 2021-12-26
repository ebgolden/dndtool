package com.ebgolden.domain.turnqueueservice.bll;

import com.ebgolden.domain.turnqueueservice.bll.bo.EncounterBo;
import com.ebgolden.domain.turnqueueservice.bll.bo.TurnQueueBo;

public interface TurnQueueBusinessLogic {
    TurnQueueBo getTurnQueueBo(EncounterBo encounterBo);
}