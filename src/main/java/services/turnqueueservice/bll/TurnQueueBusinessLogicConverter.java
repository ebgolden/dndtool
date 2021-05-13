package services.turnqueueservice.bll;

import services.turnqueueservice.TurnQueueRequest;
import services.turnqueueservice.TurnQueueResponse;
import services.turnqueueservice.bll.bo.EncounterBo;
import services.turnqueueservice.bll.bo.TurnQueueBo;

public interface TurnQueueBusinessLogicConverter {
    EncounterBo getEncounterBoFromTurnQueueRequest(TurnQueueRequest turnQueueRequest);

    TurnQueueResponse getTurnQueueResponseFromTurnQueueBo(TurnQueueBo turnQueueBo);
}