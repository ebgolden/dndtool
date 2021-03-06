package com.ebgolden.persistence.operatorservice.dal;

import com.ebgolden.common.OperatorRequestQuery;
import com.ebgolden.common.OperatorResponseQuery;
import com.ebgolden.persistence.operatorservice.bll.bo.*;
import com.ebgolden.persistence.operatorservice.dal.dao.*;

public interface OperatorDataAccessConverter {
    CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao getCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDaoFromCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo);

    QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDaoFromQueryIdAndResponseJsonBo(QueryIdAndResponseJsonBo queryIdAndResponseJsonBo);

    PlayerIdDao getPlayerIdDaoFromPlayerBo(PlayerBo playerBo);

    DungeonMasterIdDao getDungeonMasterIdDaoFromDungeonMasterBo(DungeonMasterBo dungeonMasterBo);

    QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBoFromQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao);

    PortCampaignMapBo getPortCampaignMapBoFromPortCampaignMapDao(PortCampaignMapDao portCampaignMapDao);

    CampaignBo getCampaignBoFromCampaignDao(CampaignDao campaignDao);

    PortBo getPortBoFromPortDao(PortDao portDao);

    IPAddressBo getIPAddressBoFromIPAddressDao(IPAddressDao ipAddressDao);

    OperatorRequestQuery getOperatorRequestQueryFromCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);

    OperatorRequestQuery getOperatorRequestQueryFromPlayerIdDao(PlayerIdDao playerIdDao);

    OperatorRequestQuery getOperatorRequestQueryFromDungeonMasterIdDao(DungeonMasterIdDao dungeonMasterIdDao);

    OperatorResponseQuery getOperatorResponseQueryFromQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao);

    QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDaoFromOperatorResponseQuery(OperatorResponseQuery operatorResponseQuery);

    CampaignDao getCampaignDaoFromOperatorResponseQuery(OperatorResponseQuery operatorResponseQuery);

    PortDao getPortDaoFromPort(int port);

    IPAddressDao getIPAddressDaoFromIPAddress(String ipAddress);
}