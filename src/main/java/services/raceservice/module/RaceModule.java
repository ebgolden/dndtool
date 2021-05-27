package services.raceservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.raceservice.GetUpdatedRace;
import services.raceservice.GetUpdatedRaceImpl;
import services.raceservice.bll.RaceBusinessLogic;
import services.raceservice.bll.RaceBusinessLogicConverter;
import services.raceservice.bll.RaceBusinessLogicConverterImpl;
import services.raceservice.bll.RaceBusinessLogicImpl;
import services.raceservice.dal.RaceDataAccess;
import services.raceservice.dal.RaceDataAccessConverter;
import services.raceservice.dal.RaceDataAccessConverterImpl;
import services.raceservice.dal.RaceDataAccessImpl;

public class RaceModule extends AbstractModule {
    private final Object API;

    public RaceModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetUpdatedRace.class).to(GetUpdatedRaceImpl.class);
        bind(RaceBusinessLogicConverter.class).to(RaceBusinessLogicConverterImpl.class);
        bind(RaceBusinessLogic.class).to(RaceBusinessLogicImpl.class);
        bind(RaceDataAccessConverter.class).to(RaceDataAccessConverterImpl.class);
        bind(RaceDataAccess.class).to(RaceDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}