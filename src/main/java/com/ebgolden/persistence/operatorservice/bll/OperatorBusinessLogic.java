package com.ebgolden.persistence.operatorservice.bll;

import com.ebgolden.persistence.operatorservice.bll.bo.*;

public interface OperatorBusinessLogic {
    QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);

    QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(QueryIdAndResponseJsonBo queryIdAndResponseJsonBo);

    PortCampaignMapBo getPortCampaignMapBo(PlayerBo playerBo);

    CampaignBo getCampaignBo(PlayerBo playerBo);

    PortBo getPortBo(DungeonMasterBo dungeonMasterBo);

    IPAddressBo getIPAddressBo(PlayerBo playerBo);
}