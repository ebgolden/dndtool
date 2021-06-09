package domain.raceservice.dal;

import domain.raceservice.bll.bo.RaceBo;
import domain.raceservice.dal.dao.RaceDao;

public interface RaceDataAccessConverter {
    RaceDao getRaceDaoFromRaceBo(RaceBo raceBo);

    RaceBo getRaceBoFromRaceDao(RaceDao RaceDao);

    RaceDao getRaceDaoFromRaceJson(String raceJson);
}