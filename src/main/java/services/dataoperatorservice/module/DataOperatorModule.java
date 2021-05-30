package services.dataoperatorservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.SendQuery;
import services.dataoperatorservice.SendQueryImpl;
import services.dataoperatorservice.bll.DataOperatorBusinessLogic;
import services.dataoperatorservice.bll.DataOperatorBusinessLogicConverter;
import services.dataoperatorservice.bll.DataOperatorBusinessLogicConverterImpl;
import services.dataoperatorservice.bll.DataOperatorBusinessLogicImpl;
import services.dataoperatorservice.dal.DataOperatorDataAccess;
import services.dataoperatorservice.dal.DataOperatorDataAccessConverter;
import services.dataoperatorservice.dal.DataOperatorDataAccessConverterImpl;
import services.dataoperatorservice.dal.DataOperatorDataAccessImpl;

public abstract class DataOperatorModule extends AbstractModule {
    private final Campaign CAMPAIGN;
    private final Player SENDER_PLAYER;
    private final Object API;

    public DataOperatorModule(Campaign campaign, Player senderPlayer, Object api) {
        CAMPAIGN = campaign;
        SENDER_PLAYER = senderPlayer;
        API = api;
    }

    @Override
    protected void configure() {
        bind(SendQuery.class).to(SendQueryImpl.class);
        bind(DataOperatorBusinessLogicConverter.class).to(DataOperatorBusinessLogicConverterImpl.class);
        bind(DataOperatorBusinessLogic.class).to(DataOperatorBusinessLogicImpl.class);
        bind(DataOperatorDataAccessConverter.class).to(DataOperatorDataAccessConverterImpl.class);
        bind(DataOperatorDataAccess.class).to(DataOperatorDataAccessImpl.class);
    }

    @Provides
    @Named("queryRequest")
    public QueryRequest provideQueryRequestClass() {
        return QueryRequest
                .builder()
                .campaign(CAMPAIGN)
                .senderPlayer(SENDER_PLAYER)
                .api(API)
                .build();
    }
}