package domain.campaignservice;

import com.google.inject.Inject;
import domain.campaignservice.bll.CampaignBusinessLogic;
import domain.campaignservice.bll.CampaignBusinessLogicConverter;
import domain.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import domain.campaignservice.bll.bo.CampaignBo;

public class CreateCampaignImpl implements CreateCampaign {
    @Inject
    private CampaignBusinessLogicConverter campaignBusinessLogicConverter;
    @Inject
    private CampaignBusinessLogic campaignBusinessLogic;

    public CreateCampaignResponse getCreateCampaignResponse(CreateCampaignRequest createCampaignRequest) {
        CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo = campaignBusinessLogicConverter.getCampaignAndVisibilityAndDungeonMasterBoFromCreateCampaignRequest(createCampaignRequest);
        CampaignBo campaignBo = campaignBusinessLogic.getCampaignBo(campaignAndVisibilityAndDungeonMasterBo);
        return campaignBusinessLogicConverter.getCreateCampaignResponseFromCampaignBo(campaignBo);
    }
}