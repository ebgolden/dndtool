package services.turnqueueservice;

import com.google.inject.Inject;
import services.turnqueueservice.bll.TurnQueueBusinessLogic;
import services.turnqueueservice.bll.TurnQueueBusinessLogicConverter;
import services.turnqueueservice.bll.bo.EncounterBo;
import services.turnqueueservice.bll.bo.TurnQueueBo;

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