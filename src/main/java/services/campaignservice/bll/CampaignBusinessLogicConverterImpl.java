package services.campaignservice.bll;

import objects.Campaign;
import objects.DungeonMaster;
import services.campaignservice.CreateCampaignRequest;
import services.campaignservice.CreateCampaignResponse;
import services.campaignservice.bll.bo.CampaignAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;

public class CampaignBusinessLogicConverterImpl implements CampaignBusinessLogicConverter {
    public CampaignAndDungeonMasterBo getCampaignAndDungeonMasterBoFromCreateCampaignRequest(CreateCampaignRequest createCampaignRequest) {
        Campaign campaign = createCampaignRequest.getCampaign();
        DungeonMaster dungeonMaster = createCampaignRequest.getDungeonMaster();
        return CampaignAndDungeonMasterBo
                .builder()
                .campaign(campaign)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public CreateCampaignResponse getCreateCampaignResponseFromCampaignBo(CampaignBo campaignBo) {
        Campaign campaign = campaignBo.getCampaign();
        return CreateCampaignResponse
                .builder()
                .campaign(campaign)
                .build();
    }
}