package com.ebgolden.domain.resultservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.resultservice.GetUpdatedResult;
import com.ebgolden.domain.resultservice.GetUpdatedResultImpl;
import com.ebgolden.domain.resultservice.ChangeVisibilityOfResultDetails;
import com.ebgolden.domain.resultservice.ChangeVisibilityOfResultDetailsImpl;
import com.ebgolden.domain.resultservice.bll.ResultBusinessLogic;
import com.ebgolden.domain.resultservice.bll.ResultBusinessLogicConverter;
import com.ebgolden.domain.resultservice.bll.ResultBusinessLogicConverterImpl;
import com.ebgolden.domain.resultservice.bll.ResultBusinessLogicImpl;
import com.ebgolden.domain.resultservice.dal.ResultDataAccess;
import com.ebgolden.domain.resultservice.dal.ResultDataAccessConverter;
import com.ebgolden.domain.resultservice.dal.ResultDataAccessConverterImpl;
import com.ebgolden.domain.resultservice.dal.ResultDataAccessImpl;

public class ResultModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetUpdatedResult.class).to(GetUpdatedResultImpl.class);
        bind(ChangeVisibilityOfResultDetails.class).to(ChangeVisibilityOfResultDetailsImpl.class);
        bind(ResultBusinessLogicConverter.class).to(ResultBusinessLogicConverterImpl.class);
        bind(ResultBusinessLogic.class).to(ResultBusinessLogicImpl.class);
        bind(ResultDataAccessConverter.class).to(ResultDataAccessConverterImpl.class);
        bind(ResultDataAccess.class).to(ResultDataAccessImpl.class);
    }
}