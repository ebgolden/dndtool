package services.racedetailservice.dal;

import services.racedetailservice.bll.bo.RaceBo;
import services.racedetailservice.bll.bo.RaceDetailsBo;
import services.racedetailservice.dal.dao.RaceDao;
import services.racedetailservice.dal.dao.RaceDetailsDao;

public interface RaceDetailDataAccessConverter {
    RaceDao getRaceDaoFromRaceBo(RaceBo raceBo);

    RaceDetailsBo getRaceDetailsBoFromRaceDetailsDao(RaceDetailsDao RaceDetailsDao);

    RaceDetailsDao getRaceDetailsDaoFromLatestJsonObject(String latestJsonObject);
}