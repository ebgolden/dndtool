package services.dataoperatorservice.bll;

import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
import services.dataoperatorservice.RequestQueryRequest;
import services.dataoperatorservice.RequestQueryResponse;
import services.dataoperatorservice.ResponseQueryRequest;
import services.dataoperatorservice.ResponseQueryResponse;
import services.dataoperatorservice.bll.bo.CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo;
import services.dataoperatorservice.bll.bo.QueryIdAndResponseJsonBo;

public class DataOperatorBusinessLogicConverterImpl implements DataOperatorBusinessLogicConverter {
    public CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo getCampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBoFromRequestQueryResponse(RequestQueryRequest requestQueryRequest) {
        Campaign campaign = requestQueryRequest.getCampaign();
        String campaignId = campaign.getId();
        Player senderPlayer = requestQueryRequest.getSenderPlayer();
        String senderPlayerId = senderPlayer.getId();
        Object api = requestQueryRequest.getApi();
        String apiName = api.toString();
        QueryType queryType = requestQueryRequest.getQueryType();
        String requestJson = requestQueryRequest.getRequestJson();
        return CampaignIdAndSenderPlayerIdAndAPINameAndQueryTypeAndRequestJsonBo
                .builder()
                .campaignId(campaignId)
                .senderPlayerId(senderPlayerId)
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
}