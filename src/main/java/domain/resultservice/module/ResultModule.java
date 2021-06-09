package domain.resultservice.module;

import com.google.inject.AbstractModule;
import domain.resultservice.GetUpdatedResult;
import domain.resultservice.GetUpdatedResultImpl;
import domain.resultservice.ChangeVisibilityOfResultDetails;
import domain.resultservice.ChangeVisibilityOfResultDetailsImpl;
import domain.resultservice.bll.ResultBusinessLogic;
import domain.resultservice.bll.ResultBusinessLogicConverter;
import domain.resultservice.bll.ResultBusinessLogicConverterImpl;
import domain.resultservice.bll.ResultBusinessLogicImpl;
import domain.resultservice.dal.ResultDataAccess;
import domain.resultservice.dal.ResultDataAccessConverter;
import domain.resultservice.dal.ResultDataAccessConverterImpl;
import domain.resultservice.dal.ResultDataAccessImpl;

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