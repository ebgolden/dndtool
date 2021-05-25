package services.campaignservice.bll;

import objects.Campaign;
import objects.DungeonMaster;
import objects.Visibility;
import services.campaignservice.CreateCampaignRequest;
import services.campaignservice.CreateCampaignResponse;
import services.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import services.campaignservice.bll.bo.CampaignBo;
import java.util.Map;

public class CampaignBusinessLogicConverterImpl implements CampaignBusinessLogicConverter {
    public CampaignAndVisibilityAndDungeonMasterBo getCampaignAndVisibilityAndDungeonMasterBoFromCreateCampaignRequest(CreateCampaignRequest createCampaignRequest) {
        Campaign campaign = createCampaignRequest.getCampaign();
        Map<String, Visibility> visibilityMap = createCampaignRequest.getVisibilityMap();
        DungeonMaster dungeonMaster = createCampaignRequest.getDungeonMaster();
        return CampaignAndVisibilityAndDungeonMasterBo
                .builder()
                .campaign(campaign)
                .visibilityMap(visibilityMap)
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