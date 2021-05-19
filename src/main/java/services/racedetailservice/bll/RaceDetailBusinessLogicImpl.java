package services.racedetailservice.bll;

import com.google.inject.Inject;
import services.racedetailservice.bll.bo.RaceBo;
import services.racedetailservice.bll.bo.RaceDetailsBo;
import services.racedetailservice.dal.RaceDetailDataAccess;
import services.racedetailservice.dal.RaceDetailDataAccessConverter;
import services.racedetailservice.dal.dao.RaceDao;
import services.racedetailservice.dal.dao.RaceDetailsDao;

public class RaceDetailBusinessLogicImpl implements RaceDetailBusinessLogic {
    @Inject
    private RaceDetailDataAccessConverter raceDetailDataAccessConverter;
    @Inject
    private RaceDetailDataAccess raceDetailDataAccess;

    public RaceDetailsBo getRaceDetailsBo(RaceBo raceBo) {
        RaceDao raceDao = raceDetailDataAccessConverter.getRaceDaoFromRaceBo(raceBo);
        RaceDetailsDao raceDetailsDao = raceDetailDataAccess.getRaceDetailsDao(raceDao);
        return raceDetailDataAccessConverter.getRaceDetailsBoFromRaceDetailsDao(raceDetailsDao);
    }
}