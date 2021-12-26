package domain.raceservice.module;

import com.google.inject.AbstractModule;
import domain.raceservice.GetUpdatedRace;
import domain.raceservice.GetUpdatedRaceImpl;
import domain.raceservice.bll.RaceBusinessLogic;
import domain.raceservice.bll.RaceBusinessLogicConverter;
import domain.raceservice.bll.RaceBusinessLogicConverterImpl;
import domain.raceservice.bll.RaceBusinessLogicImpl;
import domain.raceservice.dal.RaceDataAccess;
import domain.raceservice.dal.RaceDataAccessConverter;
import domain.raceservice.dal.RaceDataAccessConverterImpl;
import domain.raceservice.dal.RaceDataAccessImpl;

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