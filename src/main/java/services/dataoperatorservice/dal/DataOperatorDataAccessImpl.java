package services.dataoperatorservice.dal;

import com.google.inject.Inject;
import commonobjects.Campaign;
import commonobjects.DataOperator;
import commonobjects.DataOperatorRequestQuery;
import commonobjects.DataOperatorResponseQuery;
import services.dataoperatorservice.dal.dao.*;
import java.util.HashMap;
import java.util.Map;

public class DataOperatorDataAccessImpl implements DataOperatorDataAccess {
    @Inject
    private DataOperatorDataAccessConverter dataOperatorDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao) {
        DataOperatorRequestQuery dataOperatorRequestQuery = dataOperatorDataAccessConverter.getDataOperatorRequestQueryFromCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao(campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);
        DataOperatorResponseQuery dataOperatorResponseQuery = dataOperator.getResponseJson(dataOperatorRequestQuery);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromDataOperatorResponseQuery(dataOperatorResponseQuery);
    }

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao) {
        DataOperatorResponseQuery dataOperatorResponseQuery = dataOperatorDataAccessConverter.getDataOperatorResponseQueryFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
        dataOperatorResponseQuery = dataOperator.getResponseJson(dataOperatorResponseQuery);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromDataOperatorResponseQuery(dataOperatorResponseQuery);
    }

    public PortCampaignMapDao getPortCampaignMapDao(PlayerIdDao playerIdDao) {
        Map<Integer, Campaign> portCampaignMap = searchSocketsForCampaignsAndReturnInMap(playerIdDao);
        return PortCampaignMapDao
                .builder()
                .portCampaignMap(portCampaignMap)
                .build();
    }

    public CampaignDao getCampaignDao(PlayerIdDao playerIdDao) {
        DataOperatorRequestQuery dataOperatorRequestQuery = dataOperatorDataAccessConverter.getDataOperatorRequestQueryFromPlayerIdDao(playerIdDao);
        DataOperatorResponseQuery dataOperatorResponseQuery = dataOperator.getResponseJson(dataOperatorRequestQuery);
        return dataOperatorDataAccessConverter.getCampaignDaoFromDataOperatorResponseQuery(dataOperatorResponseQuery);
    }

    public PortDao getPortDao(DungeonMasterIdDao dungeonMasterIdDao) {
        int port = dataOperator.openAndReturnUnusedPort();
        return dataOperatorDataAccessConverter.getPortDaoFromPort(port);
    }

    public IPAddressDao getIPAddressDao(PlayerIdDao playerIdDao) {
        String ipAddress = dataOperator.findAndReturnIPAddress();
        return dataOperatorDataAccessConverter.getIPAddressDaoFromIPAddress(ipAddress);
    }

    private Map<Integer, Campaign> searchSocketsForCampaignsAndReturnInMap(PlayerIdDao playerIdDao) {
        Map<Integer, Campaign> portCampaignMap = new HashMap<>();
        int[] openPorts = dataOperator.getOpenPorts();
        for (int port : openPorts) {
            DataOperatorRequestQuery dataOperatorRequestQuery = dataOperatorDataAccessConverter.getDataOperatorRequestQueryFromPlayerIdDao(playerIdDao);
            dataOperator.setPort(port);
            DataOperatorResponseQuery dataOperatorResponseQuery = dataOperator.getResponseJson(dataOperatorRequestQuery);
            CampaignDao campaignDao = dataOperatorDataAccessConverter.getCampaignDaoFromDataOperatorResponseQuery(dataOperatorResponseQuery);
            Campaign campaign = campaignDao.getCampaign();
            if (campaign != null)
                portCampaignMap.put(port, campaign);
        }
        return portCampaignMap;
    }
}