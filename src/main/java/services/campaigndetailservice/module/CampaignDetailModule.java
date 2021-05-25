package services.campaigndetailservice.module;

import com.google.inject.AbstractModule;
import services.campaigndetailservice.GetCampaignDetails;
import services.campaigndetailservice.GetCampaignDetailsImpl;
import services.campaigndetailservice.UpdateCampaignDetailsVisibility;
import services.campaigndetailservice.UpdateCampaignDetailsVisibilityImpl;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogic;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogicConverter;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogicConverterImpl;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogicImpl;
import services.campaigndetailservice.dal.CampaignDetailDataAccess;
import services.campaigndetailservice.dal.CampaignDetailDataAccessConverter;
import services.campaigndetailservice.dal.CampaignDetailDataAccessConverterImpl;
import services.campaigndetailservice.dal.CampaignDetailDataAccessImpl;

public class CampaignDetailModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetCampaignDetails.class).to(GetCampaignDetailsImpl.class);
        bind(UpdateCampaignDetailsVisibility.class).to(UpdateCampaignDetailsVisibilityImpl.class);
        bind(CampaignDetailBusinessLogicConverter.class).to(CampaignDetailBusinessLogicConverterImpl.class);
        bind(CampaignDetailBusinessLogic.class).to(CampaignDetailBusinessLogicImpl.class);
        bind(CampaignDetailDataAccessConverter.class).to(CampaignDetailDataAccessConverterImpl.class);
        bind(CampaignDetailDataAccess.class).to(CampaignDetailDataAccessImpl.class);
    }
}