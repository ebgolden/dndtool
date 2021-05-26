package services.turnqueueservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.turnqueueservice.dal.dao.EncounterDao;
import services.turnqueueservice.dal.dao.TurnQueueDao;

public class TurnQueueDataAccessImpl implements TurnQueueDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private TurnQueueDataAccessConverter turnQueueDataAccessConverter;

    public TurnQueueDao getTurnQueueDao(EncounterDao encounterDao) {
        String encounterJson = encounterDao.getEncounterJson();
        dataOperator.sendRequestJson(encounterJson);
        String turnQueueJson = dataOperator.getResponseJson();
        return turnQueueDataAccessConverter.getTurnQueueDaoFromTurnQueueJson(turnQueueJson);
    }
}