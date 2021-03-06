package com.ebgolden.persistence.operatorservice.dal;

import com.ebgolden.persistence.operatorservice.dal.dao.*;

public interface OperatorDataAccess {
    QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);

    QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao);

    PortCampaignMapDao getPortCampaignMapDao(PlayerIdDao playerIdDao);

    CampaignDao getCampaignDao(PlayerIdDao playerIdDao);

    PortDao getPortDao(DungeonMasterIdDao dungeonMasterIdDao);

    IPAddressDao getIPAddressDao(PlayerIdDao playerIdDao);
}