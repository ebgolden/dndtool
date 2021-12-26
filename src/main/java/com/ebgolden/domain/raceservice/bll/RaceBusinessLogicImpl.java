package com.ebgolden.domain.raceservice.bll;

import com.google.inject.Inject;
import com.ebgolden.domain.raceservice.bll.bo.RaceBo;
import com.ebgolden.domain.raceservice.dal.RaceDataAccess;
import com.ebgolden.domain.raceservice.dal.RaceDataAccessConverter;
import com.ebgolden.domain.raceservice.dal.dao.RaceDao;

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