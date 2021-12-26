package com.ebgolden.domain.turnqueueservice.bll;

import com.ebgolden.common.Encounter;
import com.ebgolden.domain.turnqueueservice.TurnQueueRequest;
import com.ebgolden.domain.turnqueueservice.TurnQueueResponse;
import com.ebgolden.domain.turnqueueservice.bll.bo.EncounterBo;
import com.ebgolden.domain.turnqueueservice.bll.bo.TurnQueueBo;

public class TurnQueueBusinessLogicConverterImpl implements TurnQueueBusinessLogicConverter {
    public EncounterBo getEncounterBoFromTurnQueueRequest(TurnQueueRequest turnQueueRequest) {
        Encounter encounter = turnQueueRequest.getParty().getCurrentEncounter();
        return EncounterBo
                .builder()
                .encounter(encounter)
                .build();
    }

    public TurnQueueResponse getTurnQueueResponseFromTurnQueueBo(TurnQueueBo turnQueueBo) {
        return TurnQueueResponse
                .builder()
                .characters(turnQueueBo.getCharacters())
                .currentTurnIndex(turnQueueBo.getCurrentTurnIndex())
                .build();
    }
}