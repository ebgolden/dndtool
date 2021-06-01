package services.dataoperatorservice.dal;

import com.google.inject.Inject;
import commonobjects.Campaign;
import commonobjects.DataOperator;
import commonobjects.DataOperatorRequestQuery;
import commonobjects.DataOperatorResponseQuery;
import services.dataoperatorservice.dal.dao.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao;
import services.dataoperatorservice.dal.dao.QueryIdAndResponseJsonDao;
import services.dataoperatorservice.dal.dao.ServerSocketCampaignMapDao;
import java.net.ServerSocket;
import java.util.Map;

public class DataOperatorDataAccessImpl implements DataOperatorDataAccess {
    @Inject
    private DataOperatorDataAccessConverter dataOperatorDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao) {
        DataOperatorRequestQuery dataOperatorRequestQuery = dataOperatorDataAccessConverter.getDataOperatorRequestQueryFromCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao(campaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao);
        DataOperatorResponseQuery dataOperatorResponseQuery = dataOperator.getResponseJson(dataOperatorRequestQuery);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromDataOperatorResponseQuery(dataOperatorResponseQuery);
    }

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao) {
        DataOperatorResponseQuery dataOperatorResponseQuery = dataOperatorDataAccessConverter.getDataOperatorResponseQueryFromQueryIdAndResponseJsonDao(queryIdAndResponseJsonDao);
        dataOperatorResponseQuery = dataOperator.getResponseJson(dataOperatorResponseQuery);
        return dataOperatorDataAccessConverter.getQueryIdAndResponseJsonDaoFromDataOperatorResponseQuery(dataOperatorResponseQuery);
    }

    public ServerSocketCampaignMapDao getServerSocketCampaignMapDao() {
        Map<ServerSocket, Campaign> serverSocketCampaignMap = searchSocketsForCampaignsAndReturnInMap();
        return ServerSocketCampaignMapDao
                .builder()
                .serverSocketCampaignMap(serverSocketCampaignMap)
                .build();
    }

    private Map<ServerSocket, Campaign> searchSocketsForCampaignsAndReturnInMap() {
        //search sockets for campaigns and return as map.
        /*
         * must be able to send request to sockets that returns a Campaign object or
         * json that can be converted to Campaign object
        */
    }
}