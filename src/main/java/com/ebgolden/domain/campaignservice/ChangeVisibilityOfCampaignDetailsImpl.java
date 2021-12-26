package com.ebgolden.domain.campaignservice;

import com.google.inject.Inject;
import com.ebgolden.domain.campaignservice.bll.CampaignBusinessLogic;
import com.ebgolden.domain.campaignservice.bll.CampaignBusinessLogicConverter;
import com.ebgolden.domain.campaignservice.bll.bo.CampaignAndVisibilityAndDungeonMasterBo;
import com.ebgolden.domain.campaignservice.bll.bo.CampaignAndVisibilityBo;

public class ChangeVisibilityOfCampaignDetailsImpl implements ChangeVisibilityOfCampaignDetails {
    @Inject
    private CampaignBusinessLogicConverter campaignBusinessLogicConverter;
    @Inject
    private CampaignBusinessLogic campaignBusinessLogic;

    public ChangeVisibilityOfCampaignDetailsResponse getChangeVisibilityOfCampaignDetailsResponse(ChangeVisibilityOfCampaignDetailsRequest changeVisibilityOfUpdatedCampaignRequest) {
        CampaignAndVisibilityAndDungeonMasterBo campaignAndVisibilityAndDungeonMasterBo = campaignBusinessLogicConverter.getCampaignAndVisibilityAndDungeonMasterBoFromChangeVisibilityOfCampaignDetailsRequest(changeVisibilityOfUpdatedCampaignRequest);
        CampaignAndVisibilityBo campaignAndVisibilityBo = campaignBusinessLogic.getCampaignAndVisibilityBo(campaignAndVisibilityAndDungeonMasterBo);
        return campaignBusinessLogicConverter.getChangeVisibilityOfCampaignDetailsResponseFromCampaignAndVisibilityBo(campaignAndVisibilityBo);
    }
}