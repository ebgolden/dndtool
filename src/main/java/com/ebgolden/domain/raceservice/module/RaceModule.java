package com.ebgolden.domain.raceservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.raceservice.GetUpdatedRace;
import com.ebgolden.domain.raceservice.GetUpdatedRaceImpl;
import com.ebgolden.domain.raceservice.bll.RaceBusinessLogic;
import com.ebgolden.domain.raceservice.bll.RaceBusinessLogicConverter;
import com.ebgolden.domain.raceservice.bll.RaceBusinessLogicConverterImpl;
import com.ebgolden.domain.raceservice.bll.RaceBusinessLogicImpl;
import com.ebgolden.domain.raceservice.dal.RaceDataAccess;
import com.ebgolden.domain.raceservice.dal.RaceDataAccessConverter;
import com.ebgolden.domain.raceservice.dal.RaceDataAccessConverterImpl;
import com.ebgolden.domain.raceservice.dal.RaceDataAccessImpl;

public class RaceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetUpdatedRace.class).to(GetUpdatedRaceImpl.class);
        bind(RaceBusinessLogicConverter.class).to(RaceBusinessLogicConverterImpl.class);
        bind(RaceBusinessLogic.class).to(RaceBusinessLogicImpl.class);
        bind(RaceDataAccessConverter.class).to(RaceDataAccessConverterImpl.class);
        bind(RaceDataAccess.class).to(RaceDataAccessImpl.class);
    }
}