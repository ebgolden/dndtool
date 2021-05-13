package services.turnqueueservice.bll;

import com.google.inject.Inject;
import services.turnqueueservice.bll.bo.EncounterBo;
import services.turnqueueservice.bll.bo.TurnQueueBo;
import services.turnqueueservice.dal.TurnQueueDataAccess;
import services.turnqueueservice.dal.TurnQueueDataAccessConverter;
import services.turnqueueservice.dal.dao.EncounterDao;
import services.turnqueueservice.dal.dao.TurnQueueDao;

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