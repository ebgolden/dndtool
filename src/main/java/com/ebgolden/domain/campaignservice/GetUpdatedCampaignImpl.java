package com.ebgolden.domain.campaignservice;

import com.google.inject.Inject;
import com.ebgolden.domain.campaignservice.bll.CampaignBusinessLogic;
import com.ebgolden.domain.campaignservice.bll.CampaignBusinessLogicConverter;
import com.ebgolden.domain.campaignservice.bll.bo.CampaignAndPlayerBo;
import com.ebgolden.domain.campaignservice.bll.bo.CampaignAndVisibilityBo;

public class GetUpdatedCampaignImpl implements GetUpdatedCampaign {
    @Inject
    private CampaignBusinessLogicConverter campaignBusinessLogicConverter;
    @Inject
    private CampaignBusinessLogic campaignBusinessLogic;

    public UpdatedCampaignResponse getUpdatedCampaignResponse(UpdatedCampaignRequest updatedCampaignRequest) {
        CampaignAndPlayerBo campaignAndPlayerBo = campaignBusinessLogicConverter.getCampaignAndPlayerBoFromUpdatedCampaignRequest(updatedCampaignRequest);
        CampaignAndVisibilityBo campaignAndVisibilityBo = campaignBusinessLogic.getCampaignAndVisibilityBo(campaignAndPlayerBo);
        return campaignBusinessLogicConverter.getUpdatedCampaignResponseFromCampaignAndVisibilityBo(campaignAndVisibilityBo);
    }
}