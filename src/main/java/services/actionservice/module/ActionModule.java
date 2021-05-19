package services.actionservice.module;

import com.google.inject.AbstractModule;
import services.actionservice.GetActions;
import services.actionservice.GetActionsImpl;
import services.actionservice.TakeAction;
import services.actionservice.TakeActionImpl;
import services.actionservice.bll.ActionBusinessLogic;
import services.actionservice.bll.ActionBusinessLogicConverter;
import services.actionservice.bll.ActionBusinessLogicConverterImpl;
import services.actionservice.bll.ActionBusinessLogicImpl;
import services.actionservice.dal.ActionDataAccess;
import services.actionservice.dal.ActionDataAccessConverter;
import services.actionservice.dal.ActionDataAccessConverterImpl;
import services.actionservice.dal.ActionDataAccessImpl;

public class ActionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetActions.class).to(GetActionsImpl.class);
        bind(TakeAction.class).to(TakeActionImpl.class);
        bind(ActionBusinessLogicConverter.class).to(ActionBusinessLogicConverterImpl.class);
        bind(ActionBusinessLogic.class).to(ActionBusinessLogicImpl.class);
        bind(ActionDataAccessConverter.class).to(ActionDataAccessConverterImpl.class);
        bind(ActionDataAccess.class).to(ActionDataAccessImpl.class);
    }
}