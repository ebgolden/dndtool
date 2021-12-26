package com.ebgolden.domain.campaignservice.bll;

import com.ebgolden.domain.campaignservice.*;
import com.ebgolden.domain.campaignservice.bll.bo.*;

public interface CampaignBusinessLogicConverter {
    CampaignAndVisibilityAndDungeonMasterBo getCampaignAndVisibilityAndDungeonMasterBoFromCreateCampaignRequest(CreateCampaignRequest createCampaignRequest);

    CampaignAndPlayerBo getCampaignAndPlayerBoFromUpdatedCampaignRequest(UpdatedCampaignRequest updatedCampaignRequest);

    CampaignAndVisibilityAndDungeonMasterBo getCampaignAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfCampaignDetailsRequest(ChangeVisibilityOfCampaignDetailsRequest changeVisibilityOfUpdatedCampaignRequest);

    CampaignAndPlayerAndDungeonMasterBo getCampaignAndPlayerAndDungeonMasterBoFromAddPlayerToCampaignRequest(AddPlayerToCampaignRequest addPlayerToCampaignRequest);

    CampaignAndPlayerAndDungeonMasterBo getCampaignAndPlayerAndDungeonMasterBoFromRemovePlayerFromCampaignRequest(RemovePlayerFromCampaignRequest removePlayerFromCampaignRequest);

    CreateCampaignResponse getCreateCampaignResponseFromCampaignBo(CampaignBo campaignBo);

    UpdatedCampaignResponse getUpdatedCampaignResponseFromCampaignAndVisibilityBo(CampaignAndVisibilityBo campaignAndVisibilityBo);

    ChangeVisibilityOfCampaignDetailsResponse getChangeVisibilityOfCampaignDetailsResponseFromCampaignAndVisibilityBo(CampaignAndVisibilityBo campaignAndVisibilityBo);

    AddPlayerToCampaignResponse getAddPlayerToCampaignResponseFromCampaignBo(CampaignBo campaignBo);

    RemovePlayerFromCampaignResponse getRemovePlayerFromCampaignResponseFromCampaignBo(CampaignBo campaignBo);

    CampaignAndVisibilityBo getCampaignAndVisibilityBoFromCampaignAndVisibilityAndDungeonMasterBo(CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo);
}