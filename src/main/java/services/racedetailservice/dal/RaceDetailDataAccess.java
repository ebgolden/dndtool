package services.racedetailservice.dal;

import services.racedetailservice.dal.dao.RaceDao;
import services.racedetailservice.dal.dao.RaceDetailsDao;

public interface RaceDetailDataAccess {
    RaceDetailsDao getRaceDetailsDao(RaceDao raceDao);
}