package services.turnqueueservice.bll;

import objects.Encounter;
import services.turnqueueservice.TurnQueueRequest;
import services.turnqueueservice.TurnQueueResponse;
import services.turnqueueservice.bll.bo.EncounterBo;
import services.turnqueueservice.bll.bo.TurnQueueBo;

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