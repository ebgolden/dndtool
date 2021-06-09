package services.dataoperatorservice.bll;

import services.dataoperatorservice.bll.bo.*;

public interface DataOperatorBusinessLogic {
    QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);

    QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBo(QueryIdAndResponseJsonBo queryIdAndResponseJsonBo);

    PortCampaignMapBo getPortCampaignMapBo(PlayerBo playerBo);

    CampaignBo getCampaignBo(PlayerBo playerBo);

    PortBo getPortBo(DungeonMasterBo dungeonMasterBo);

    IPAddressBo getIPAddressBo(PlayerBo playerBo);
}