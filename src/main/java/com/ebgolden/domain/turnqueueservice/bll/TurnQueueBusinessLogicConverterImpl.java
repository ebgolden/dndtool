package domain.turnqueueservice.bll;

import common.Encounter;
import domain.turnqueueservice.TurnQueueRequest;
import domain.turnqueueservice.TurnQueueResponse;
import domain.turnqueueservice.bll.bo.EncounterBo;
import domain.turnqueueservice.bll.bo.TurnQueueBo;

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