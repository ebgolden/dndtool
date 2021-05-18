package services.turnqueueservice.module;

import com.google.inject.AbstractModule;
import services.turnqueueservice.GetTurnQueue;
import services.turnqueueservice.GetTurnQueueImpl;
import services.turnqueueservice.bll.TurnQueueBusinessLogic;
import services.turnqueueservice.bll.TurnQueueBusinessLogicImpl;
import services.turnqueueservice.bll.TurnQueueBusinessLogicConverter;
import services.turnqueueservice.bll.TurnQueueBusinessLogicConverterImpl;
import services.turnqueueservice.dal.TurnQueueDataAccess;
import services.turnqueueservice.dal.TurnQueueDataAccessConverter;
import services.turnqueueservice.dal.TurnQueueDataAccessConverterImpl;
import services.turnqueueservice.dal.TurnQueueDataAccessImpl;

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