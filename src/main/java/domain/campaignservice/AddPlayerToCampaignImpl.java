package domain.campaignservice;

import com.google.inject.Inject;
import domain.campaignservice.bll.CampaignBusinessLogic;
import domain.campaignservice.bll.CampaignBusinessLogicConverter;
import domain.campaignservice.bll.bo.CampaignAndPlayerAndDungeonMasterBo;
import domain.campaignservice.bll.bo.CampaignBo;

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