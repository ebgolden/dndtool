package services.dataoperatorservice.dal;

import services.dataoperatorservice.dal.dao.*;

public interface DataOperatorDataAccess {
    QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);

    QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao);

    PortCampaignMapDao getPortCampaignMapDao(PlayerIdDao playerIdDao);

    CampaignDao getCampaignDao(PlayerIdDao playerIdDao);

    PortDao getPortDao(DungeonMasterIdDao dungeonMasterIdDao);

    IPAddressDao getIPAddressDao(PlayerIdDao playerIdDao);
}