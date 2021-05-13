package services.turnqueueservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.turnqueueservice.dal.dao.EncounterDao;
import services.turnqueueservice.dal.dao.TurnQueueDao;

public class TurnQueueDataAccessImpl implements TurnQueueDataAccess {
    @Inject
    private TurnQueueDataAccessConverter turnQueueDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public TurnQueueDao getTurnQueueDao(EncounterDao encounterDao) {
        String encounterJson = encounterDao.getEncounterJson();
        dataOperator.sendRequestJson(encounterJson);
        String latestObjectJson = dataOperator.getResponseJson();
        return turnQueueDataAccessConverter.getTurnQueueDaoFromLatestObjectJson(latestObjectJson);
    }
}