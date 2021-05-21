package services.locationdetailservice.module;

import com.google.inject.AbstractModule;
import services.locationdetailservice.GetLocationDetails;
import services.locationdetailservice.GetLocationDetailsImpl;
import services.locationdetailservice.bll.LocationDetailBusinessLogic;
import services.locationdetailservice.bll.LocationDetailBusinessLogicConverter;
import services.locationdetailservice.bll.LocationDetailBusinessLogicConverterImpl;
import services.locationdetailservice.bll.LocationDetailBusinessLogicImpl;
import services.locationdetailservice.dal.LocationDetailDataAccess;
import services.locationdetailservice.dal.LocationDetailDataAccessConverter;
import services.locationdetailservice.dal.LocationDetailDataAccessConverterImpl;
import services.locationdetailservice.dal.LocationDetailDataAccessImpl;

public class LocationDetailModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetLocationDetails.class).to(GetLocationDetailsImpl.class);
        bind(LocationDetailBusinessLogicConverter.class).to(LocationDetailBusinessLogicConverterImpl.class);
        bind(LocationDetailBusinessLogic.class).to(LocationDetailBusinessLogicImpl.class);
        bind(LocationDetailDataAccessConverter.class).to(LocationDetailDataAccessConverterImpl.class);
        bind(LocationDetailDataAccess.class).to(LocationDetailDataAccessImpl.class);
    }
}