package com.ebgolden.domain.worldservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.worldservice.GetUpdatedWorldDetails;
import com.ebgolden.domain.worldservice.GetUpdatedWorldDetailsImpl;
import com.ebgolden.domain.worldservice.ChangeVisibilityOfWorldDetails;
import com.ebgolden.domain.worldservice.ChangeVisibilityOfWorldDetailsImpl;
import com.ebgolden.domain.worldservice.bll.WorldBusinessLogic;
import com.ebgolden.domain.worldservice.bll.WorldBusinessLogicConverter;
import com.ebgolden.domain.worldservice.bll.WorldBusinessLogicConverterImpl;
import com.ebgolden.domain.worldservice.bll.WorldBusinessLogicImpl;
import com.ebgolden.domain.worldservice.dal.WorldDataAccess;
import com.ebgolden.domain.worldservice.dal.WorldDataAccessConverter;
import com.ebgolden.domain.worldservice.dal.WorldDataAccessConverterImpl;
import com.ebgolden.domain.worldservice.dal.WorldDataAccessImpl;

public class WorldModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetUpdatedWorldDetails.class).to(GetUpdatedWorldDetailsImpl.class);
        bind(ChangeVisibilityOfWorldDetails.class).to(ChangeVisibilityOfWorldDetailsImpl.class);
        bind(WorldBusinessLogicConverter.class).to(WorldBusinessLogicConverterImpl.class);
        bind(WorldBusinessLogic.class).to(WorldBusinessLogicImpl.class);
        bind(WorldDataAccessConverter.class).to(WorldDataAccessConverterImpl.class);
        bind(WorldDataAccess.class).to(WorldDataAccessImpl.class);
    }
}