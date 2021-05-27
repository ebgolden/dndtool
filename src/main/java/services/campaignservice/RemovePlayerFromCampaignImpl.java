package services.campaignservice;

import com.google.inject.Inject;
import services.campaignservice.bll.CampaignBusinessLogic;
import services.campaignservice.bll.CampaignBusinessLogicConverter;
import services.campaignservice.bll.bo.CampaignAndPlayerAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;

public class RemovePlayerFromCampaignImpl implements RemovePlayerFromCampaign {
    @Inject
    private CampaignBusinessLogicConverter campaignBusinessLogicConverter;
    @Inject
    private CampaignBusinessLogic campaignBusinessLogic;

    public RemovePlayerFromCampaignResponse getRemovePlayerFromCampaignResponse(RemovePlayerFromCampaignRequest removePlayerFromCampaignRequest) {
        CampaignAndPlayerAndDungeonMasterBo campaignAndPlayerAndDungeonMasterBo = campaignBusinessLogicConverter.getCampaignAndPlayerAndDungeonMasterBoFromRemovePlayerFromCampaignRequest(removePlayerFromCampaignRequest);
        CampaignBo campaignBo = campaignBusinessLogic.getCampaignBo(campaignAndPlayerAndDungeonMasterBo);
        return campaignBusinessLogicConverter.getRemovePlayerFromCampaignResponseFromCampaignBo(campaignBo);
    }
}