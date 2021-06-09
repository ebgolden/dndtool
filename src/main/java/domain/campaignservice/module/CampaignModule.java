package domain.campaignservice.module;

import com.google.inject.AbstractModule;
import domain.campaignservice.*;
import domain.campaignservice.bll.CampaignBusinessLogic;
import domain.campaignservice.bll.CampaignBusinessLogicConverter;
import domain.campaignservice.bll.CampaignBusinessLogicConverterImpl;
import domain.campaignservice.bll.CampaignBusinessLogicImpl;
import domain.campaignservice.dal.CampaignDataAccess;
import domain.campaignservice.dal.CampaignDataAccessConverter;
import domain.campaignservice.dal.CampaignDataAccessConverterImpl;
import domain.campaignservice.dal.CampaignDataAccessImpl;

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