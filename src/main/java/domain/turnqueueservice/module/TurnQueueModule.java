package domain.turnqueueservice.module;

import com.google.inject.AbstractModule;
import domain.turnqueueservice.GetTurnQueue;
import domain.turnqueueservice.GetTurnQueueImpl;
import domain.turnqueueservice.bll.TurnQueueBusinessLogic;
import domain.turnqueueservice.bll.TurnQueueBusinessLogicImpl;
import domain.turnqueueservice.bll.TurnQueueBusinessLogicConverter;
import domain.turnqueueservice.bll.TurnQueueBusinessLogicConverterImpl;
import domain.turnqueueservice.dal.TurnQueueDataAccess;
import domain.turnqueueservice.dal.TurnQueueDataAccessConverter;
import domain.turnqueueservice.dal.TurnQueueDataAccessConverterImpl;
import domain.turnqueueservice.dal.TurnQueueDataAccessImpl;

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