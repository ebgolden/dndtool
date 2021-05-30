package services.dataoperatorservice.bll;

import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.QueryRequest;
import services.dataoperatorservice.QueryResponse;
import services.dataoperatorservice.bll.bo.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo;
import services.dataoperatorservice.bll.bo.QueryIdAndResponseJsonBo;

public class DataOperatorBusinessLogicConverterImpl implements DataOperatorBusinessLogicConverter {
    public CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo getCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBoFromQueryRequest(QueryRequest queryRequest) {
        Campaign campaign = queryRequest.getCampaign();
        String campaignId = campaign.getId();
        Player senderPlayer = queryRequest.getSenderPlayer();
        String senderPlayerId = senderPlayer.getId();
        Object api = queryRequest.getApi();
        String apiName = api.toString();
        QueryType queryType = queryRequest.getQueryType();
        String requestJson = queryRequest.getRequestJson();
        return CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo
                .builder()
                .campaignId(campaignId)
                .senderPlayerId(senderPlayerId)
                .apiName(apiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
    }

    public QueryResponse getQueryResponseFromQueryIdAndResponseJsonBo(QueryIdAndResponseJsonBo queryIdAndResponseJsonBo) {
        String queryId = queryIdAndResponseJsonBo.getQueryId();
        String responseJson = queryIdAndResponseJsonBo.getResponseJson();
        return QueryResponse
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }
}