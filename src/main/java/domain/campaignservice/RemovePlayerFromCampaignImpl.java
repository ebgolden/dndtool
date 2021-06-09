package domain.campaignservice;

import com.google.inject.Inject;
import domain.campaignservice.bll.CampaignBusinessLogic;
import domain.campaignservice.bll.CampaignBusinessLogicConverter;
import domain.campaignservice.bll.bo.CampaignAndPlayerAndDungeonMasterBo;
import domain.campaignservice.bll.bo.CampaignBo;

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