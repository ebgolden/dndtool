package com.ebgolden.persistence.operatorservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.Player;
import com.ebgolden.persistence.operatorservice.*;
import com.ebgolden.persistence.operatorservice.dal.OperatorDataAccessConverterImpl;
import com.ebgolden.persistence.operatorservice.dal.OperatorDataAccessImpl;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogic;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogicConverter;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogicConverterImpl;
import com.ebgolden.persistence.operatorservice.bll.OperatorBusinessLogicImpl;
import com.ebgolden.persistence.operatorservice.dal.OperatorDataAccess;
import com.ebgolden.persistence.operatorservice.dal.OperatorDataAccessConverter;

public abstract class OperatorModule extends AbstractModule {
    private final Campaign CAMPAIGN;
    private final Player SENDER_PLAYER;
    private final Object API;

    public OperatorModule(Campaign campaign, Player player, Object api) {
        CAMPAIGN = campaign;
        SENDER_PLAYER = player;
        API = api;
    }

    public OperatorModule(Player player, Object api) {
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
        bind(OperatorBusinessLogicConverter.class).to(OperatorBusinessLogicConverterImpl.class);
        bind(OperatorBusinessLogic.class).to(OperatorBusinessLogicImpl.class);
        bind(OperatorDataAccessConverter.class).to(OperatorDataAccessConverterImpl.class);
        bind(OperatorDataAccess.class).to(OperatorDataAccessImpl.class);
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