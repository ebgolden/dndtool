package com.ebgolden.domain.locationservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.locationservice.GetUpdatedLocation;
import com.ebgolden.domain.locationservice.GetUpdatedLocationImpl;
import com.ebgolden.domain.locationservice.ChangeVisibilityOfLocationDetails;
import com.ebgolden.domain.locationservice.ChangeVisibilityOfLocationDetailsImpl;
import com.ebgolden.domain.locationservice.bll.LocationBusinessLogic;
import com.ebgolden.domain.locationservice.bll.LocationBusinessLogicConverter;
import com.ebgolden.domain.locationservice.bll.LocationBusinessLogicConverterImpl;
import com.ebgolden.domain.locationservice.bll.LocationBusinessLogicImpl;
import com.ebgolden.domain.locationservice.dal.LocationDataAccess;
import com.ebgolden.domain.locationservice.dal.LocationDataAccessConverter;
import com.ebgolden.domain.locationservice.dal.LocationDataAccessConverterImpl;
import com.ebgolden.domain.locationservice.dal.LocationDataAccessImpl;

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