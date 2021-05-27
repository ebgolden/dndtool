package services.raceservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.DataOperator;
import services.raceservice.dal.dao.RaceDao;

public class RaceDataAccessImpl implements RaceDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private RaceDataAccessConverter raceDataAccessConverter;

    public RaceDao getRaceDao(RaceDao raceDao) {
        String raceJson = raceDao.getRaceJson();
        dataOperator.sendRequestJson(api, raceJson);
        raceJson = dataOperator.getResponseJson();
        return raceDataAccessConverter.getRaceDaoFromRaceJson(raceJson);
    }
}