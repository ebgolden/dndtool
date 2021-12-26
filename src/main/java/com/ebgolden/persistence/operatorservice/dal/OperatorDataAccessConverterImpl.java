package com.ebgolden.persistence.operatorservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebgolden.common.*;
import com.ebgolden.persistence.operatorservice.bll.bo.*;
import com.ebgolden.persistence.operatorservice.dal.dao.*;
import java.util.Map;

public class OperatorDataAccessConverterImpl implements OperatorDataAccessConverter {
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

    public IPAddressBo getIPAddressBoFromIPAddressDao(IPAddressDao ipAddressDao) {
        String ipAddress = ipAddressDao.getIPAddress();
        return IPAddressBo
                .builder()
                .ipAddress(ipAddress)
                .build();
    }

    public OperatorRequestQuery getOperatorRequestQueryFromCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao(CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao) {
        String campaignId = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getCampaignId();
        String playerId = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getPlayerId();
        String apiName = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getApiName();
        String queryType = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getQueryType();
        String requestJson = campaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonDao.getRequestJson();
        return OperatorRequestQuery
                .builder()
                .campaignId(campaignId)
                .playerId(playerId)
                .apiName(apiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }

    public OperatorRequestQuery getOperatorRequestQueryFromPlayerIdDao(PlayerIdDao playerIdDao) {
        String playerId = playerIdDao.getPlayerId();
        return OperatorRequestQuery
                .builder()
                .playerId(playerId)
                .build();
    }

    public OperatorRequestQuery getOperatorRequestQueryFromDungeonMasterIdDao(DungeonMasterIdDao dungeonMasterIdDao) {
        String dungeonMasterId = dungeonMasterIdDao.getDungeonMasterId();
        return OperatorRequestQuery
                .builder()
                .playerId(dungeonMasterId)
                .build();
    }

    public OperatorResponseQuery getOperatorResponseQueryFromQueryIdAndResponseJsonDao(QueryIdAndResponseJsonDao queryIdAndResponseJsonDao) {
        String queryId = queryIdAndResponseJsonDao.getQueryId();
        String responseJson = queryIdAndResponseJsonDao.getResponseJson();
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public QueryIdAndResponseJsonDao getQueryIdAndResponseJsonDaoFromOperatorResponseQuery(OperatorResponseQuery operatorResponseQuery) {
        String queryId = operatorResponseQuery.getQueryId();
        String responseJson = operatorResponseQuery.getResponseJson();
        return QueryIdAndResponseJsonDao
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public CampaignDao getCampaignDaoFromOperatorResponseQuery(OperatorResponseQuery operatorResponseQuery) {
        String responseJson = operatorResponseQuery.getResponseJson();
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

    public IPAddressDao getIPAddressDaoFromIPAddress(String ipAddress) {
        return IPAddressDao
                .builder()
                .ipAddress(ipAddress)
                .build();
    }
}