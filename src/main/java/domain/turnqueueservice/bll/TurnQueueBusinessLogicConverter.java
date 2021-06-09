package domain.turnqueueservice.bll;

import domain.turnqueueservice.TurnQueueRequest;
import domain.turnqueueservice.TurnQueueResponse;
import domain.turnqueueservice.bll.bo.EncounterBo;
import domain.turnqueueservice.bll.bo.TurnQueueBo;

public interface TurnQueueBusinessLogicConverter {
    EncounterBo getEncounterBoFromTurnQueueRequest(TurnQueueRequest turnQueueRequest);

    TurnQueueResponse getTurnQueueResponseFromTurnQueueBo(TurnQueueBo turnQueueBo);
}