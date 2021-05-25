package services.campaignservice.module;

import com.google.inject.AbstractModule;
import services.campaignservice.CreateCampaign;
import services.campaignservice.CreateCampaignImpl;
import services.campaignservice.bll.CampaignBusinessLogic;
import services.campaignservice.bll.CampaignBusinessLogicConverter;
import services.campaignservice.bll.CampaignBusinessLogicConverterImpl;
import services.campaignservice.bll.CampaignBusinessLogicImpl;
import services.campaignservice.dal.CampaignDataAccess;
import services.campaignservice.dal.CampaignDataAccessConverter;
import services.campaignservice.dal.CampaignDataAccessConverterImpl;
import services.campaignservice.dal.CampaignDataAccessImpl;

public class CampaignModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CreateCampaign.class).to(CreateCampaignImpl.class);
        bind(CampaignBusinessLogicConverter.class).to(CampaignBusinessLogicConverterImpl.class);
        bind(CampaignBusinessLogic.class).to(CampaignBusinessLogicImpl.class);
        bind(CampaignDataAccessConverter.class).to(CampaignDataAccessConverterImpl.class);
        bind(CampaignDataAccess.class).to(CampaignDataAccessImpl.class);
    }
}