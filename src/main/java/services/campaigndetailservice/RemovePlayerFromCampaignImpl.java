package services.campaigndetailservice;

import com.google.inject.Inject;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogic;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogicConverter;
import services.campaigndetailservice.bll.bo.CampaignAndPlayerAndDungeonMasterBo;
import services.campaigndetailservice.bll.bo.CampaignDetailsBo;

public class RemovePlayerFromCampaignImpl implements RemovePlayerFromCampaign {
    @Inject
    private CampaignDetailBusinessLogicConverter campaignDetailBusinessLogicConverter;
    @Inject
    private CampaignDetailBusinessLogic campaignDetailBusinessLogic;

    public RemovePlayerFromCampaignResponse getRemovePlayerFromCampaignResponse(RemovePlayerFromCampaignRequest removePlayerFromCampaignRequest) {
        CampaignAndPlayerAndDungeonMasterBo campaignAndPlayerAndDungeonMasterBo = campaignDetailBusinessLogicConverter.getCampaignAndPlayerAndDungeonMasterBoFromRemovePlayerFromCampaignRequest(removePlayerFromCampaignRequest);
        CampaignDetailsBo campaignDetailsBo = campaignDetailBusinessLogic.getCampaignDetailsBo(campaignAndPlayerAndDungeonMasterBo);
        return campaignDetailBusinessLogicConverter.getRemovePlayerFromCampaignResponseFromCampaignDetailsBo(campaignDetailsBo);
    }
}