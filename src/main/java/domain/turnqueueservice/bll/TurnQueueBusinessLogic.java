package domain.turnqueueservice.bll;

import domain.turnqueueservice.bll.bo.EncounterBo;
import domain.turnqueueservice.bll.bo.TurnQueueBo;

public interface TurnQueueBusinessLogic {
    TurnQueueBo getTurnQueueBo(EncounterBo encounterBo);
}