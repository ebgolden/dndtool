package services.dataoperatorservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonobjects.*;
import services.dataoperatorservice.bll.bo.*;
import services.dataoperatorservice.dal.dao.*;
import java.util.Map;

public class DataOperatorDataAccessConverterImpl implements DataOperatorDataAccessConverter {
    public CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao getCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDaoFromCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo) {
        String campaignId = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getCampaignId();
        String playerId = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getPlayerId();
        String apiName = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getApiName();
        QueryType queryType = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getQueryType();
        String queryTypeName = queryType.getQueryType();
        String requestJson = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo.getRequestJson();
        return CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao
                .builder()
                .campaignId(campaignId)
                .playerId(playerId)
                .apiName(apiName)
                .queryType(queryTypeName)
                .requestJson(requestJson)
                .build();
    }

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDaoFromQueryIdAndResponseJsonBo(QueryIdAndResponseJsonBo queryIdAndResponseJsonBo) {
        String queryId = queryIdAndResponseJsonBo.getQueryId();
        String responseJson = queryIdAndResponseJsonBo.getResponseJson();
        return QueryIdAndResponseJsonDao
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public PlayerIdDao getPlayerIdDaoFromPlayerBo(PlayerBo playerBo) {
        Player player = playerBo.getPlayer();
        String playerId = player.getId();
        return PlayerIdDao
                .builder()
                .playerId(playerId)
                .build();
    }

    public DungeonMasterIdDao getDungeonMasterIdDaoFromDungeonMasterBo(DungeonMasterBo dungeonMasterBo) {
        DungeonMaster dungeonMaster = dungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        return DungeonMasterIdDao
                .builder()
                .dungeonMasterId(dungeonMasterId)
                .build();
    }

    public QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBoFromQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao) {
        String queryId = queryIdAndResponseJsonDao.getQueryId();
        String responseJson = queryIdAndResponseJsonDao.getResponseJson();
        return QueryIdAndResponseJsonBo
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public PortCampaignMapBo getPortCampaignMapBoFromPortCampaignMapDao(PortCampaignMapDao portCampaignMapDao) {
        Map<Integer, Campaign> portCampaignMap = portCampaignMapDao.getPortCampaignMap();
        return PortCampaignMapBo
                .builder()
                .portCampaignMap(portCampaignMap)
                .build();
    }

    public CampaignBo getCampaignBoFromCampaignDao(CampaignDao campaignDao) {
        Campaign campaign = campaignDao.getCampaign();
        return CampaignBo
                .builder()
                .campaign(campaign)
                .build();
    }

    public PortBo getPortBoFromPortDao(PortDao portDao) {
        int port = portDao.getPort();
        return PortBo
                .builder()
                .port(port)
                .build();
    }

    public DataOperatorRequestQuery getDataOperatorRequestQueryFromCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao) {
        String campaignId = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getCampaignId();
        String playerId = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getPlayerId();
        String apiName = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getApiName();
        String queryType = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getQueryType();
        String requestJson = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getRequestJson();
        return DataOperatorRequestQuery
                .builder()
                .campaignId(campaignId)
                .playerId(playerId)
                .apiName(apiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }

    public DataOperatorRequestQuery getDataOperatorRequestQueryFromPlayerIdDao(PlayerIdDao playerIdDao) {
        String playerId = playerIdDao.getPlayerId();
        return DataOperatorRequestQuery
                .builder()
                .playerId(playerId)
                .build();
    }

    public DataOperatorRequestQuery getDataOperatorRequestQueryFromDungeonMasterIdDao(DungeonMasterIdDao dungeonMasterIdDao) {
        String dungeonMasterId = dungeonMasterIdDao.getDungeonMasterId();
        return DataOperatorRequestQuery
                .builder()
                .playerId(dungeonMasterId)
                .build();
    }

    public DataOperatorResponseQuery getDataOperatorResponseQueryFromQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao) {
        String queryId = queryIdAndResponseJsonDao.getQueryId();
        String responseJson = queryIdAndResponseJsonDao.getResponseJson();
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDaoFromDataOperatorResponseQuery(DataOperatorResponseQuery dataOperatorResponseQuery) {
        String queryId = dataOperatorResponseQuery.getQueryId();
        String responseJson = dataOperatorResponseQuery.getResponseJson();
        return QueryIdAndResponseJsonDao
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public CampaignDao getCampaignDaoFromDataOperatorResponseQuery(DataOperatorResponseQuery dataOperatorResponseQuery) {
        String responseJson = dataOperatorResponseQuery.getResponseJson();
        if (responseJson == null)
            responseJson = "{}";
        Campaign campaign = null;
        if (!responseJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            campaign = Campaign
                    .builder()
                    .build();
            try {
                campaign = objectMapper.readValue(responseJson, Campaign.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return CampaignDao
                .builder()
                .campaign(campaign)
                .build();
    }

    public PortDao getPortDaoFromPort(int port) {
        return PortDao
                .builder()
                .port(port)
                .build();
    }
}