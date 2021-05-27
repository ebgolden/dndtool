package services.racedetailservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.racedetailservice.GetRaceDetails;
import services.racedetailservice.GetRaceDetailsImpl;
import services.racedetailservice.bll.RaceDetailBusinessLogic;
import services.racedetailservice.bll.RaceDetailBusinessLogicConverter;
import services.racedetailservice.bll.RaceDetailBusinessLogicConverterImpl;
import services.racedetailservice.bll.RaceDetailBusinessLogicImpl;
import services.racedetailservice.dal.RaceDetailDataAccess;
import services.racedetailservice.dal.RaceDetailDataAccessConverter;
import services.racedetailservice.dal.RaceDetailDataAccessConverterImpl;
import services.racedetailservice.dal.RaceDetailDataAccessImpl;

public class RaceDetailModule extends AbstractModule {
    private final Object API;

    public RaceDetailModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetRaceDetails.class).to(GetRaceDetailsImpl.class);
        bind(RaceDetailBusinessLogicConverter.class).to(RaceDetailBusinessLogicConverterImpl.class);
        bind(RaceDetailBusinessLogic.class).to(RaceDetailBusinessLogicImpl.class);
        bind(RaceDetailDataAccessConverter.class).to(RaceDetailDataAccessConverterImpl.class);
        bind(RaceDetailDataAccess.class).to(RaceDetailDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}