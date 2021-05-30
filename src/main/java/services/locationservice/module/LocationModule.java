package services.locationservice.module;

import com.google.inject.AbstractModule;
import services.locationservice.GetUpdatedLocation;
import services.locationservice.GetUpdatedLocationImpl;
import services.locationservice.ChangeVisibilityOfLocationDetails;
import services.locationservice.ChangeVisibilityOfLocationDetailsImpl;
import services.locationservice.bll.LocationBusinessLogic;
import services.locationservice.bll.LocationBusinessLogicConverter;
import services.locationservice.bll.LocationBusinessLogicConverterImpl;
import services.locationservice.bll.LocationBusinessLogicImpl;
import services.locationservice.dal.LocationDataAccess;
import services.locationservice.dal.LocationDataAccessConverter;
import services.locationservice.dal.LocationDataAccessConverterImpl;
import services.locationservice.dal.LocationDataAccessImpl;

public class LocationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetUpdatedLocation.class).to(GetUpdatedLocationImpl.class);
        bind(ChangeVisibilityOfLocationDetails.class).to(ChangeVisibilityOfLocationDetailsImpl.class);
        bind(LocationBusinessLogicConverter.class).to(LocationBusinessLogicConverterImpl.class);
        bind(LocationBusinessLogic.class).to(LocationBusinessLogicImpl.class);
        bind(LocationDataAccessConverter.class).to(LocationDataAccessConverterImpl.class);
        bind(LocationDataAccess.class).to(LocationDataAccessImpl.class);
    }
}