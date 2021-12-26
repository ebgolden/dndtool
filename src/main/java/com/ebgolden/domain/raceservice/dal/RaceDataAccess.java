package domain.raceservice.dal;

import domain.raceservice.dal.dao.RaceDao;

public interface RaceDataAccess {
    RaceDao getRaceDao(RaceDao raceDao);
}