package domain.turnqueueservice.bll;

import com.google.inject.Inject;
import domain.turnqueueservice.bll.bo.EncounterBo;
import domain.turnqueueservice.bll.bo.TurnQueueBo;
import domain.turnqueueservice.dal.TurnQueueDataAccess;
import domain.turnqueueservice.dal.TurnQueueDataAccessConverter;
import domain.turnqueueservice.dal.dao.EncounterDao;
import domain.turnqueueservice.dal.dao.TurnQueueDao;

public class TurnQueueBusinessLogicImpl implements TurnQueueBusinessLogic {
    @Inject
    private TurnQueueDataAccessConverter turnQueueDataAccessConverter;
    @Inject
    private TurnQueueDataAccess turnQueueDataAccess;

    public TurnQueueBo getTurnQueueBo(EncounterBo encounterBo) {
        EncounterDao encounterDao = turnQueueDataAccessConverter.getEncounterDaoFromEncounterBo(encounterBo);
        TurnQueueDao turnQueueDao = turnQueueDataAccess.getTurnQueueDao(encounterDao);
        return turnQueueDataAccessConverter.getTurnQueueBoFromTurnQueueDao(turnQueueDao);
    }
}