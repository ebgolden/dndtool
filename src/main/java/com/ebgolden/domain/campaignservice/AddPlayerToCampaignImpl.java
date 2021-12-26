package com.ebgolden.domain.campaignservice;

import com.google.inject.Inject;
import com.ebgolden.domain.campaignservice.bll.CampaignBusinessLogic;
import com.ebgolden.domain.campaignservice.bll.CampaignBusinessLogicConverter;
import com.ebgolden.domain.campaignservice.bll.bo.CampaignAndPlayerAndDungeonMasterBo;
import com.ebgolden.domain.campaignservice.bll.bo.CampaignBo;

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