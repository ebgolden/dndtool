package services.resultservice.module;

import com.google.inject.AbstractModule;
import services.resultservice.GetUpdatedResult;
import services.resultservice.GetUpdatedResultImpl;
import services.resultservice.ChangeVisibilityOfResultDetails;
import services.resultservice.ChangeVisibilityOfResultDetailsImpl;
import services.resultservice.bll.ResultBusinessLogic;
import services.resultservice.bll.ResultBusinessLogicConverter;
import services.resultservice.bll.ResultBusinessLogicConverterImpl;
import services.resultservice.bll.ResultBusinessLogicImpl;
import services.resultservice.dal.ResultDataAccess;
import services.resultservice.dal.ResultDataAccessConverter;
import services.resultservice.dal.ResultDataAccessConverterImpl;
import services.resultservice.dal.ResultDataAccessImpl;

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