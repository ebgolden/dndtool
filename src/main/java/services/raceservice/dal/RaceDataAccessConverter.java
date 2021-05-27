package services.raceservice.dal;

import services.raceservice.bll.bo.RaceBo;
import services.raceservice.dal.dao.RaceDao;

public interface RaceDataAccessConverter {
    RaceDao getRaceDaoFromRaceBo(RaceBo raceBo);

    RaceBo getRaceBoFromRaceDao(RaceDao RaceDao);

    RaceDao getRaceDaoFromRaceJson(String raceJson);
}