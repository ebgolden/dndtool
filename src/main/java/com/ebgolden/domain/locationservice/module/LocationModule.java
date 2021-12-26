package domain.locationservice.module;

import com.google.inject.AbstractModule;
import domain.locationservice.GetUpdatedLocation;
import domain.locationservice.GetUpdatedLocationImpl;
import domain.locationservice.ChangeVisibilityOfLocationDetails;
import domain.locationservice.ChangeVisibilityOfLocationDetailsImpl;
import domain.locationservice.bll.LocationBusinessLogic;
import domain.locationservice.bll.LocationBusinessLogicConverter;
import domain.locationservice.bll.LocationBusinessLogicConverterImpl;
import domain.locationservice.bll.LocationBusinessLogicImpl;
import domain.locationservice.dal.LocationDataAccess;
import domain.locationservice.dal.LocationDataAccessConverter;
import domain.locationservice.dal.LocationDataAccessConverterImpl;
import domain.locationservice.dal.LocationDataAccessImpl;

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