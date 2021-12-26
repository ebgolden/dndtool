package com.ebgolden.domain.campaignservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.campaignservice.*;
import com.ebgolden.domain.campaignservice.bll.CampaignBusinessLogic;
import com.ebgolden.domain.campaignservice.bll.CampaignBusinessLogicConverter;
import com.ebgolden.domain.campaignservice.bll.CampaignBusinessLogicConverterImpl;
import com.ebgolden.domain.campaignservice.bll.CampaignBusinessLogicImpl;
import com.ebgolden.domain.campaignservice.dal.CampaignDataAccess;
import com.ebgolden.domain.campaignservice.dal.CampaignDataAccessConverter;
import com.ebgolden.domain.campaignservice.dal.CampaignDataAccessConverterImpl;
import com.ebgolden.domain.campaignservice.dal.CampaignDataAccessImpl;

public class CampaignModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CreateCampaign.class).to(CreateCampaignImpl.class);
        bind(GetUpdatedCampaign.class).to(GetUpdatedCampaignImpl.class);
        bind(ChangeVisibilityOfCampaignDetails.class).to(ChangeVisibilityOfCampaignDetailsImpl.class);
        bind(AddPlayerToCampaign.class).to(AddPlayerToCampaignImpl.class);
        bind(RemovePlayerFromCampaign.class).to(RemovePlayerFromCampaignImpl.class);
        bind(CampaignBusinessLogicConverter.class).to(CampaignBusinessLogicConverterImpl.class);
        bind(CampaignBusinessLogic.class).to(CampaignBusinessLogicImpl.class);
        bind(CampaignDataAccessConverter.class).to(CampaignDataAccessConverterImpl.class);
        bind(CampaignDataAccess.class).to(CampaignDataAccessImpl.class);
    }
}