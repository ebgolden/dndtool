package services.racedetailservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import objects.DataOperator;
import services.racedetailservice.dal.dao.RaceDao;
import services.racedetailservice.dal.dao.RaceDetailsDao;

public class RaceDetailDataAccessImpl implements RaceDetailDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private RaceDetailDataAccessConverter raceDetailDataAccessConverter;

    public RaceDetailsDao getRaceDetailsDao(RaceDao raceDao) {
        String raceJson = raceDao.getRaceJson();
        dataOperator.sendRequestJson(api, raceJson);
        String raceDetailsJson = dataOperator.getResponseJson();
        return raceDetailDataAccessConverter.getRaceDetailsDaoFromRaceDetailsJson(raceDetailsJson);
    }
}