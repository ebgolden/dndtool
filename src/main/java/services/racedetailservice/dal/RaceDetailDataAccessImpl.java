package services.racedetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.racedetailservice.dal.dao.RaceDao;
import services.racedetailservice.dal.dao.RaceDetailsDao;

public class RaceDetailDataAccessImpl implements RaceDetailDataAccess {
    @Inject
    RaceDetailDataAccessConverter raceDetailDataAccessConverter;
    @Inject
    DataOperator dataOperator;

    public RaceDetailsDao getRaceDetailsDao(RaceDao raceDao) {
        String raceJson = raceDao.getRaceJson();
        dataOperator.sendRequestJson(raceJson);
        String latestJsonObject = dataOperator.getResponseJson();
        return raceDetailDataAccessConverter.getRaceDetailsDaoFromLatestJsonObject(latestJsonObject);
    }
}