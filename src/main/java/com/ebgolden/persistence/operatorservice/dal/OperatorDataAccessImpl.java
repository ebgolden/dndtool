package com.ebgolden.persistence.operatorservice.dal;

import com.google.inject.Inject;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.Operator;
import com.ebgolden.common.OperatorRequestQuery;
import com.ebgolden.common.OperatorResponseQuery;
import com.ebgolden.persistence.operatorservice.dal.dao.*;
import java.util.HashMap;
import java.util.Map;

public class OperatorDataAccessImpl implements OperatorDataAccess {
    @Inject
    private OperatorDataAccessConverter operatorDataAccessConverter;
    @Inject
    private Operator operator;

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao) {
        OperatorRequestQuery operatorRequestQuery = operatorDataAccessConverter.getOperatorRequestQueryFromCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao(campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);
        OperatorResponseQuery operatorResponseQuery = operator.getOperatorResponseQuery(operatorRequestQuery);
        return operatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromOperatorResponseQuery(operatorResponseQuery);
    }

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao) {
        OperatorResponseQuery operatorResponseQuery = operatorDataAccessConverter.getOperatorResponseQueryFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
        operatorResponseQuery = operator.getOperatorResponseQuery(operatorResponseQuery);
        return operatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromOperatorResponseQuery(operatorResponseQuery);
    }

    public PortCampaignMapDao getPortCampaignMapDao(PlayerIdDao playerIdDao) {
        Map<Integer, Campaign> portCampaignMap = searchSocketsForCampaignsAndReturnInMap(playerIdDao);
        return PortCampaignMapDao
                .builder()
                .portCampaignMap(portCampaignMap)
                .build();
    }

    public CampaignDao getCampaignDao(PlayerIdDao playerIdDao) {
        OperatorRequestQuery operatorRequestQuery = operatorDataAccessConverter.getOperatorRequestQueryFromPlayerIdDao(playerIdDao);
        OperatorResponseQuery operatorResponseQuery = operator.getOperatorResponseQuery(operatorRequestQuery);
        return operatorDataAccessConverter.getCampaignDaoFromOperatorResponseQuery(operatorResponseQuery);
    }

    public PortDao getPortDao(DungeonMasterIdDao dungeonMasterIdDao) {
        int port = operator.openAndReturnUnusedPort();
        return operatorDataAccessConverter.getPortDaoFromPort(port);
    }

    public IPAddressDao getIPAddressDao(PlayerIdDao playerIdDao) {
        String ipAddress = operator.findAndReturnIPAddress();
        return operatorDataAccessConverter.getIPAddressDaoFromIPAddress(ipAddress);
    }

    private Map<Integer, Campaign> searchSocketsForCampaignsAndReturnInMap(PlayerIdDao playerIdDao) {
        Map<Integer, Campaign> portCampaignMap = new HashMap<>();
        int[] openPorts = operator.getOpenPorts();
        for (int port : openPorts) {
            OperatorRequestQuery operatorRequestQuery = operatorDataAccessConverter.getOperatorRequestQueryFromPlayerIdDao(playerIdDao);
            operator.setPort(port);
            OperatorResponseQuery operatorResponseQuery = operator.getOperatorResponseQuery(operatorRequestQuery);
            CampaignDao campaignDao = operatorDataAccessConverter.getCampaignDaoFromOperatorResponseQuery(operatorResponseQuery);
            Campaign campaign = campaignDao.getCampaign();
            if (campaign != null)
                portCampaignMap.put(port, campaign);
        }
        return portCampaignMap;
    }
}