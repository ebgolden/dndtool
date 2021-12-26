package com.ebgolden.domain.raceservice.dal;

import com.ebgolden.domain.raceservice.dal.dao.RaceDao;

public interface RaceDataAccess {
    RaceDao getRaceDao(RaceDao raceDao);
}