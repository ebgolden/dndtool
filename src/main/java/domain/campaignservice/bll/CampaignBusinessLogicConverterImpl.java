package domain.campaignservice.bll;

import common.Campaign;
import common.DungeonMaster;
import common.Player;
import common.Visibility;
import domain.campaignservice.*;
import domain.campaignservice.bll.bo.*;
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

    public CampaignAndPlayerBo getCampaignAndPlayerBoFromUpdatedCampaignRequest(UpdatedCampaignRequest updatedCampaignRequest) {
        Campaign campaign = updatedCampaignRequest.getCampaign();
        Player player = updatedCampaignRequest.getPlayer();
        return CampaignAndPlayerBo
                .builder()
                .campaign(campaign)
                .player(player)
                .build();
    }

    public CampaignAndVisibilityAndDungeonMasterBo getCampaignAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfCampaignDetailsRequest(ChangeVisibilityOfCampaignDetailsRequest changeVisibilityOfUpdatedCampaignRequest) {
        Campaign campaign = changeVisibilityOfUpdatedCampaignRequest.getCampaign();
        Map<String, Visibility> visibilityMap = changeVisibilityOfUpdatedCampaignRequest.getVisibilityMap();
        DungeonMaster dungeonMaster = changeVisibilityOfUpdatedCampaignRequest.getDungeonMaster();
        return CampaignAndVisibilityAndDungeonMasterBo
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

    public CampaignAndPlayerAndDungeonMasterBo getCampaignAndPlayerAndDungeonMasterBoFromRemovePlayerFromCampaignRequest(RemovePlayerFromCampaignRequest removePlayerFromCampaignRequest) {
        Campaign campaign = removePlayerFromCampaignRequest.getCampaign();
        Player player = removePlayerFromCampaignRequest.getPlayer();
        DungeonMaster dungeonMaster = removePlayerFromCampaignRequest.getDungeonMaster();
        return CampaignAndPlayerAndDungeonMasterBo
                .builder()
                .campaign(campaign)
                .player(player)
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

    public UpdatedCampaignResponse getUpdatedCampaignResponseFromCampaignAndVisibilityBo(CampaignAndVisibilityBo campaignAndVisibilityBo) {
        Campaign campaign = campaignAndVisibilityBo.getCampaign();
        return UpdatedCampaignResponse
                .builder()
                .campaign(campaign)
                .build();
    }

    public ChangeVisibilityOfCampaignDetailsResponse getChangeVisibilityOfCampaignDetailsResponseFromCampaignAndVisibilityBo(CampaignAndVisibilityBo campaignAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = campaignAndVisibilityBo.getVisibilityMap();
        return ChangeVisibilityOfCampaignDetailsResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public AddPlayerToCampaignResponse getAddPlayerToCampaignResponseFromCampaignBo(CampaignBo campaignBo) {
        Campaign campaign = campaignBo.getCampaign();
        return AddPlayerToCampaignResponse
                .builder()
                .campaign(campaign)
                .build();
    }

    public RemovePlayerFromCampaignResponse getRemovePlayerFromCampaignResponseFromCampaignBo(CampaignBo campaignBo) {
        Campaign campaign = campaignBo.getCampaign();
        return RemovePlayerFromCampaignResponse
                .builder()
                .campaign(campaign)
                .build();
    }

    public CampaignAndVisibilityBo getCampaignAndVisibilityBoFromCampaignAndVisibilityAndDungeonMasterBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo) {
        Campaign campaign = campaignAndVisibilityAndDungeonMasterBo.getCampaign();
        Map<String, Visibility> visibilityMap = campaignAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        return CampaignAndVisibilityBo
                .builder()
                .campaign(campaign)
                .visibilityMap(visibilityMap)
                .build();
    }
}