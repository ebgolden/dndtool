package com.ebgolden.domain.turnqueueservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.turnqueueservice.GetTurnQueue;
import com.ebgolden.domain.turnqueueservice.GetTurnQueueImpl;
import com.ebgolden.domain.turnqueueservice.bll.TurnQueueBusinessLogic;
import com.ebgolden.domain.turnqueueservice.bll.TurnQueueBusinessLogicImpl;
import com.ebgolden.domain.turnqueueservice.bll.TurnQueueBusinessLogicConverter;
import com.ebgolden.domain.turnqueueservice.bll.TurnQueueBusinessLogicConverterImpl;
import com.ebgolden.domain.turnqueueservice.dal.TurnQueueDataAccess;
import com.ebgolden.domain.turnqueueservice.dal.TurnQueueDataAccessConverter;
import com.ebgolden.domain.turnqueueservice.dal.TurnQueueDataAccessConverterImpl;
import com.ebgolden.domain.turnqueueservice.dal.TurnQueueDataAccessImpl;

public class TurnQueueModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetTurnQueue.class).to(GetTurnQueueImpl.class);
        bind(TurnQueueBusinessLogicConverter.class).to(TurnQueueBusinessLogicConverterImpl.class);
        bind(TurnQueueBusinessLogic.class).to(TurnQueueBusinessLogicImpl.class);
        bind(TurnQueueDataAccessConverter.class).to(TurnQueueDataAccessConverterImpl.class);
        bind(TurnQueueDataAccess.class).to(TurnQueueDataAccessImpl.class);
    }
}