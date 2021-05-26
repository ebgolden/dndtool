package services.campaigndetailservice.bll;

import objects.Campaign;
import objects.DungeonMaster;
import objects.Player;
import objects.Visibility;
import services.campaigndetailservice.*;
import services.campaigndetailservice.bll.bo.*;
import java.util.Map;

public class CampaignDetailBusinessLogicConverterImpl implements CampaignDetailBusinessLogicConverter {
    public CampaignAndPlayerBo getCampaignAndPlayerBoFromCampaignDetailsRequest(CampaignDetailsRequest campaignDetailsRequest) {
        Campaign campaign = campaignDetailsRequest.getCampaign();
        Player player = campaignDetailsRequest.getPlayer();
        return CampaignAndPlayerBo
                .builder()
                .campaign(campaign)
                .player(player)
                .build();
    }

    public CampaignDetailsAndVisibilityAndDungeonMasterBo getCampaignDetailsAndVisibilityAndDungeonMasterBoFromCampaignDetailsVisibilityRequest(CampaignDetailsVisibilityRequest campaignDetailsVisibilityRequest) {
        Campaign campaign = campaignDetailsVisibilityRequest.getCampaign();
        Map<String, Visibility> visibilityMap = campaignDetailsVisibilityRequest.getVisibilityMap();
        DungeonMaster dungeonMaster = campaignDetailsVisibilityRequest.getDungeonMaster();
        return CampaignDetailsAndVisibilityAndDungeonMasterBo
                .builder()
                .campaign(campaign)
                .visibilityMap(visibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public CampaignAndPlayerAndDungeonMasterBo getCampaignAndPlayerAndDungeonMasterBoFromAddPlayerToCampaignRequest(AddPlayerToCampaignRequest addPlayerToCampaignRequest) {
        Campaign campaign = addPlayerToCampaignRequest.getCampaign();
        Player player = addPlayerToCampaignRequest.getPlayer();
        DungeonMaster dungeonMaster = addPlayerToCampaignRequest.getDungeonMaster();
        return CampaignAndPlayerAndDungeonMasterBo
                .builder()
                .campaign(campaign)
                .player(player)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public CampaignDetailsResponse getCampaignDetailsResponseFromCampaignDetailsAndVisibilityBo(CampaignDetailsAndVisibilityBo campaignDetailsAndVisibilityBo) {
        Campaign campaign = campaignDetailsAndVisibilityBo.getCampaign();
        return CampaignDetailsResponse
                .builder()
                .campaign(campaign)
                .build();
    }

    public CampaignDetailsVisibilityResponse getCampaignDetailsVisibilityResponseFromCampaignDetailsAndVisibilityBo(CampaignDetailsAndVisibilityBo campaignDetailsAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = campaignDetailsAndVisibilityBo.getVisibilityMap();
        return CampaignDetailsVisibilityResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public AddPlayerToCampaignResponse getAddPlayerToCampaignResponseFromCampaignDetailsBo(CampaignDetailsBo campaignDetailsBo) {
        Campaign campaign = campaignDetailsBo.getCampaign();
        return AddPlayerToCampaignResponse
                .builder()
                .campaign(campaign)
                .build();
    }

    public CampaignDetailsAndVisibilityBo getCampaignDetailsAndVisibilityBoFromCampaignDetailsAndVisibilityAndDungeonMasterBo(CampaignDetailsAndVisibilityAndDungeonMasterBo campaignDetailsAndVisibilityAndDungeonMasterBo) {
        Campaign campaign = campaignDetailsAndVisibilityAndDungeonMasterBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignDetailsAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        return CampaignDetailsAndVisibilityBo
                .builder()
                .campaign(campaign)
                .visibilityMap(visibilityMap)
                .build();
    }
}