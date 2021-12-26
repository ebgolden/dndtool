package domain.turnqueueservice;

import com.google.inject.Inject;
import domain.turnqueueservice.bll.TurnQueueBusinessLogic;
import domain.turnqueueservice.bll.TurnQueueBusinessLogicConverter;
import domain.turnqueueservice.bll.bo.EncounterBo;
import domain.turnqueueservice.bll.bo.TurnQueueBo;

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