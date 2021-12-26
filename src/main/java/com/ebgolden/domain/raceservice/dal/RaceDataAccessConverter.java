package com.ebgolden.domain.raceservice.dal;

import com.ebgolden.domain.raceservice.bll.bo.RaceBo;
import com.ebgolden.domain.raceservice.dal.dao.RaceDao;

public interface RaceDataAccessConverter {
    RaceDao getRaceDaoFromRaceBo(RaceBo raceBo);

    RaceBo getRaceBoFromRaceDao(RaceDao RaceDao);

    RaceDao getRaceDaoFromRaceJson(String raceJson);
}