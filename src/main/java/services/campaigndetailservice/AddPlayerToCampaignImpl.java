package services.campaigndetailservice;

import com.google.inject.Inject;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogic;
import services.campaigndetailservice.bll.CampaignDetailBusinessLogicConverter;
import services.campaigndetailservice.bll.bo.CampaignAndPlayerAndDungeonMasterBo;
import services.campaigndetailservice.bll.bo.CampaignDetailsBo;

public class AddPlayerToCampaignImpl implements AddPlayerToCampaign {
    @Inject
    private CampaignDetailBusinessLogicConverter campaignDetailBusinessLogicConverter;
    @Inject
    private CampaignDetailBusinessLogic campaignDetailBusinessLogic;

    public AddPlayerToCampaignResponse getAddPlayerToCampaignResponse(AddPlayerToCampaignRequest addPlayerToCampaignRequest) {
        CampaignAndPlayerAndDungeonMasterBo campaignAndPlayerAndDungeonMasterBo = campaignDetailBusinessLogicConverter.getCampaignAndPlayerAndDungeonMasterBoFromAddPlayerToCampaignRequest(addPlayerToCampaignRequest);
        CampaignDetailsBo campaignDetailsBo = campaignDetailBusinessLogic.getCampaignDetailsBo(campaignAndPlayerAndDungeonMasterBo);
        return campaignDetailBusinessLogicConverter.getAddPlayerToCampaignResponseFromCampaignDetailsBo(campaignDetailsBo);
    }
}