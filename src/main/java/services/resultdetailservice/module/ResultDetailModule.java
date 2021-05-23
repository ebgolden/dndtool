package services.resultdetailservice.module;

import com.google.inject.AbstractModule;
import services.resultdetailservice.GetResultDetails;
import services.resultdetailservice.GetResultDetailsImpl;
import services.resultdetailservice.UpdateResultDetailsVisibility;
import services.resultdetailservice.UpdateResultDetailsVisibilityImpl;
import services.resultdetailservice.bll.ResultDetailBusinessLogic;
import services.resultdetailservice.bll.ResultDetailBusinessLogicConverter;
import services.resultdetailservice.bll.ResultDetailBusinessLogicConverterImpl;
import services.resultdetailservice.bll.ResultDetailBusinessLogicImpl;
import services.resultdetailservice.dal.ResultDetailDataAccess;
import services.resultdetailservice.dal.ResultDetailDataAccessConverter;
import services.resultdetailservice.dal.ResultDetailDataAccessConverterImpl;
import services.resultdetailservice.dal.ResultDetailDataAccessImpl;

public class ResultDetailModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetResultDetails.class).to(GetResultDetailsImpl.class);
        bind(UpdateResultDetailsVisibility.class).to(UpdateResultDetailsVisibilityImpl.class);
        bind(ResultDetailBusinessLogicConverter.class).to(ResultDetailBusinessLogicConverterImpl.class);
        bind(ResultDetailBusinessLogic.class).to(ResultDetailBusinessLogicImpl.class);
        bind(ResultDetailDataAccessConverter.class).to(ResultDetailDataAccessConverterImpl.class);
        bind(ResultDetailDataAccess.class).to(ResultDetailDataAccessImpl.class);
    }
}