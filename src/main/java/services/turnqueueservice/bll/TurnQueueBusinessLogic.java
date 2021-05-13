package services.turnqueueservice.bll;

import services.turnqueueservice.bll.bo.EncounterBo;
import services.turnqueueservice.bll.bo.TurnQueueBo;

public interface TurnQueueBusinessLogic {
    TurnQueueBo getTurnQueueBo(EncounterBo encounterBo);
}