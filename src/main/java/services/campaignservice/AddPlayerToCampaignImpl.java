package services.campaignservice;

import com.google.inject.Inject;
import services.campaignservice.bll.CampaignBusinessLogic;
import services.campaignservice.bll.CampaignBusinessLogicConverter;
import services.campaignservice.bll.bo.CampaignAndPlayerAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;

public class AddPlayerToCampaignImpl implements AddPlayerToCampaign {
    @Inject
    private CampaignBusinessLogicConverter campaignBusinessLogicConverter;
    @Inject
    private CampaignBusinessLogic campaignBusinessLogic;

    public AddPlayerToCampaignResponse getAddPlayerToCampaignResponse(AddPlayerToCampaignRequest addPlayerToCampaignRequest) {
        CampaignAndPlayerAndDungeonMasterBo campaignAndPlayerAndDungeonMasterBo = campaignBusinessLogicConverter.getCampaignAndPlayerAndDungeonMasterBoFromAddPlayerToCampaignRequest(addPlayerToCampaignRequest);
        CampaignBo campaignBo = campaignBusinessLogic.getCampaignBo(campaignAndPlayerAndDungeonMasterBo);
        return campaignBusinessLogicConverter.getAddPlayerToCampaignResponseFromCampaignBo(campaignBo);
    }
}