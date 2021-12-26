package com.ebgolden.domain.actionservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.actionservice.*;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogic;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogicConverter;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogicConverterImpl;
import com.ebgolden.domain.actionservice.bll.ActionBusinessLogicImpl;
import com.ebgolden.domain.actionservice.dal.ActionDataAccess;
import com.ebgolden.domain.actionservice.dal.ActionDataAccessConverter;
import com.ebgolden.domain.actionservice.dal.ActionDataAccessConverterImpl;
import com.ebgolden.domain.actionservice.dal.ActionDataAccessImpl;

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