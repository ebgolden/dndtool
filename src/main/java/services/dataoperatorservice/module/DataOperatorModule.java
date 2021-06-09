package services.dataoperatorservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import commonobjects.Campaign;
import commonobjects.Player;
import services.dataoperatorservice.*;
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

    public DataOperatorModule(Campaign campaign, Player player, Object api) {
        CAMPAIGN = campaign;
        SENDER_PLAYER = player;
        API = api;
    }

    public DataOperatorModule(Player player, Object api) {
        CAMPAIGN = null;
        SENDER_PLAYER = player;
        API = api;
    }

    @Override
    protected void configure() {
        bind(SendRequestQuery.class).to(SendRequestQueryImpl.class);
        bind(SendResponseQuery.class).to(SendResponseQueryImpl.class);
        bind(GetCampaignListOnNetwork.class).to(GetCampaignListOnNetworkImpl.class);
        bind(GetCampaignOnNetwork.class).to(GetCampaignOnNetworkImpl.class);
        bind(OpenCampaignOnNetwork.class).to(OpenCampaignOnNetworkImpl.class);
        bind(GetCampaignNetworkAddress.class).to(GetCampaignNetworkAddressImpl.class);
        bind(DataOperatorBusinessLogicConverter.class).to(DataOperatorBusinessLogicConverterImpl.class);
        bind(DataOperatorBusinessLogic.class).to(DataOperatorBusinessLogicImpl.class);
        bind(DataOperatorDataAccessConverter.class).to(DataOperatorDataAccessConverterImpl.class);
        bind(DataOperatorDataAccess.class).to(DataOperatorDataAccessImpl.class);
    }

    @Provides
    @Named("requestQueryRequest")
    public RequestQueryRequest provideRequestQueryRequestClass() {
        return RequestQueryRequest
                .builder()
                .campaign(CAMPAIGN)
                .player(SENDER_PLAYER)
                .api(API)
                .build();
    }

    @SuppressWarnings("unused")
    public abstract int provideTimeout();
}