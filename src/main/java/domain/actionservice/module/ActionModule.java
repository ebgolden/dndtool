package domain.actionservice.module;

import com.google.inject.AbstractModule;
import domain.actionservice.*;
import domain.actionservice.bll.ActionBusinessLogic;
import domain.actionservice.bll.ActionBusinessLogicConverter;
import domain.actionservice.bll.ActionBusinessLogicConverterImpl;
import domain.actionservice.bll.ActionBusinessLogicImpl;
import domain.actionservice.dal.ActionDataAccess;
import domain.actionservice.dal.ActionDataAccessConverter;
import domain.actionservice.dal.ActionDataAccessConverterImpl;
import domain.actionservice.dal.ActionDataAccessImpl;

public class ActionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetActions.class).to(GetActionsImpl.class);
        bind(TakeAction.class).to(TakeActionImpl.class);
        bind(GetActionFromNonStandardAction.class).to(GetActionFromNonStandardActionImpl.class);
        bind(GetUpdatedAction.class).to(GetUpdatedActionImpl.class);
        bind(ChangeVisibilityOfActionDetails.class).to(ChangeVisibilityOfActionDetailsImpl.class);
        bind(ActionBusinessLogicConverter.class).to(ActionBusinessLogicConverterImpl.class);
        bind(ActionBusinessLogic.class).to(ActionBusinessLogicImpl.class);
        bind(ActionDataAccessConverter.class).to(ActionDataAccessConverterImpl.class);
        bind(ActionDataAccess.class).to(ActionDataAccessImpl.class);
    }
}