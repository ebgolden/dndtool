package services.raceservice.bll;

import com.google.inject.Inject;
import services.raceservice.bll.bo.RaceBo;
import services.raceservice.dal.RaceDataAccess;
import services.raceservice.dal.RaceDataAccessConverter;
import services.raceservice.dal.dao.RaceDao;

public class RaceBusinessLogicImpl implements RaceBusinessLogic {
    @Inject
    private RaceDataAccessConverter raceDataAccessConverter;
    @Inject
    private RaceDataAccess raceDataAccess;

    public RaceBo getRaceBo(RaceBo raceBo) {
        RaceDao raceDao = raceDataAccessConverter.getRaceDaoFromRaceBo(raceBo);
        raceDao = raceDataAccess.getRaceDao(raceDao);
        return raceDataAccessConverter.getRaceBoFromRaceDao(raceDao);
    }
}