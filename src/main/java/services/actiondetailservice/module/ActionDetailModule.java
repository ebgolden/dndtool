package services.actiondetailservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.actiondetailservice.GetActionDetails;
import services.actiondetailservice.GetActionDetailsImpl;
import services.actiondetailservice.UpdateActionDetailsVisibility;
import services.actiondetailservice.UpdateActionDetailsVisibilityImpl;
import services.actiondetailservice.bll.ActionDetailBusinessLogic;
import services.actiondetailservice.bll.ActionDetailBusinessLogicConverter;
import services.actiondetailservice.bll.ActionDetailBusinessLogicConverterImpl;
import services.actiondetailservice.bll.ActionDetailBusinessLogicImpl;
import services.actiondetailservice.dal.ActionDetailDataAccess;
import services.actiondetailservice.dal.ActionDetailDataAccessConverter;
import services.actiondetailservice.dal.ActionDetailDataAccessConverterImpl;
import services.actiondetailservice.dal.ActionDetailDataAccessImpl;

public class ActionDetailModule extends AbstractModule {
    private final Object API;

    public ActionDetailModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetActionDetails.class).to(GetActionDetailsImpl.class);
        bind(UpdateActionDetailsVisibility.class).to(UpdateActionDetailsVisibilityImpl.class);
        bind(ActionDetailBusinessLogicConverter.class).to(ActionDetailBusinessLogicConverterImpl.class);
        bind(ActionDetailBusinessLogic.class).to(ActionDetailBusinessLogicImpl.class);
        bind(ActionDetailDataAccessConverter.class).to(ActionDetailDataAccessConverterImpl.class);
        bind(ActionDetailDataAccess.class).to(ActionDetailDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}