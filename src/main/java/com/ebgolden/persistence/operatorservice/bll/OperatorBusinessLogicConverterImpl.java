package com.ebgolden.persistence.operatorservice.bll;

import com.ebgolden.common.Campaign;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import com.ebgolden.persistence.operatorservice.*;
import com.ebgolden.persistence.operatorservice.bll.bo.*;
import java.util.Map;

public class OperatorBusinessLogicConverterImpl implements OperatorBusinessLogicConverter {
    public CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo getCampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBoFromRequestQueryResponse(RequestQueryRequest requestQueryRequest) {
        Campaign campaign = requestQueryRequest.getCampaign();
        String campaignId = campaign.getId();
        Player player = requestQueryRequest.getPlayer();
        String playerId = player.getId();
        Object api = requestQueryRequest.getApi();
        String apiName = api.toString();
        QueryType queryType = requestQueryRequest.getQueryType();
        String requestJson = requestQueryRequest.getRequestJson();
        return CampaignIdAndPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo
                .builder()
                .campaignId(campaignId)
                .playerId(playerId)
                .apiName(apiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }

    public QueryIdAndResponseJsonBo getQueryIdAndResponseJsonBoFromResponseQueryRequest(ResponseQueryRequest responseQueryRequest) {
        String queryId = responseQueryRequest.getQueryId();
        String responseJson = responseQueryRequest.getResponseJson();
        return QueryIdAndResponseJsonBo
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public PlayerBo getPlayerBoFromCampaignListOnNetworkRequest(CampaignListOnNetworkRequest campaignListOnNetworkRequest) {
        Player player = campaignListOnNetworkRequest.getPlayer();
        return PlayerBo
                .builder()
                .player(player)
                .build();
    }

    public PlayerBo getPlayerBoFromCampaignOnNetworkRequest(CampaignOnNetworkRequest campaignOnNetworkRequest) {
        Player player = campaignOnNetworkRequest.getPlayer();
        return PlayerBo
                .builder()
                .player(player)
                .build();
    }

    public DungeonMasterBo getDungeonMasterBoFromOpenCampaignOnNetworkRequest(OpenCampaignOnNetworkRequest openCampaignOnNetworkRequest) {
        DungeonMaster dungeonMaster = openCampaignOnNetworkRequest.getDungeonMaster();
        return DungeonMasterBo
                .builder()
                .dungeonMaster(dungeonMaster)
                .build();
    }

    public PlayerBo getPlayerBoFromCampaignNetworkAddressRequest(CampaignNetworkAddressRequest campaignNetworkAddressRequest) {
        Player player = campaignNetworkAddressRequest.getPlayer();
        return PlayerBo
                .builder()
                .player(player)
                .build();
    }

    public RequestQueryResponse getRequestQueryResponseFromQueryIdAndResponseJsonBo(QueryIdAndResponseJsonBo queryIdAndResponseJsonBo) {
        String queryId = queryIdAndResponseJsonBo.getQueryId();
        String responseJson = queryIdAndResponseJsonBo.getResponseJson();
        return RequestQueryResponse
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public ResponseQueryResponse getResponseQueryResponseFromQueryIdAndResponseJsonBo(QueryIdAndResponseJsonBo queryIdAndResponseJsonBo) {
        String queryId = queryIdAndResponseJsonBo.getQueryId();
        String responseJson = queryIdAndResponseJsonBo.getResponseJson();
        return ResponseQueryResponse
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public CampaignListOnNetworkResponse getCampaignListOnNetworkResponseFromPortCampaignMapBo(PortCampaignMapBo portCampaignMapBo) {
        Map<Integer, Campaign> portCampaignMap = portCampaignMapBo.getPortCampaignMap();
        return CampaignListOnNetworkResponse
                .builder()
                .portCampaignMap(portCampaignMap)
                .build();
    }

    public CampaignOnNetworkResponse getCampaignOnNetworkResponseFromCampaignBo(CampaignBo campaignBo) {
        Campaign campaign = campaignBo.getCampaign();
        return CampaignOnNetworkResponse
                .builder()
                .campaign(campaign)
                .build();
    }

    public OpenCampaignOnNetworkResponse getOpenCampaignOnNetworkResponseFromPortBo(PortBo portBo) {
        int port = portBo.getPort();
        return OpenCampaignOnNetworkResponse
                .builder()
                .port(port)
                .build();
    }

    public CampaignNetworkAddressResponse getCampaignNetworkAddressResponseFromIPAddressBo(IPAddressBo ipAddressBo) {
        String ipAddress = ipAddressBo.getIPAddress();
        return CampaignNetworkAddressResponse
                .builder()
                .ipAddress(ipAddress)
                .build();
    }
}