package domain.raceservice.bll;

import com.google.inject.Inject;
import domain.raceservice.bll.bo.RaceBo;
import domain.raceservice.dal.RaceDataAccess;
import domain.raceservice.dal.RaceDataAccessConverter;
import domain.raceservice.dal.dao.RaceDao;

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