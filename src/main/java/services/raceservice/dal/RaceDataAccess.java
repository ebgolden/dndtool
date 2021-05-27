package services.raceservice.dal;

import services.raceservice.dal.dao.RaceDao;

public interface RaceDataAccess {
    RaceDao getRaceDao(RaceDao raceDao);
}