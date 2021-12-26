package com.ebgolden.common;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.*;

public class SingleDeviceOperator implements Operator {
    @Inject
    @Named("queryMap")
    private Map<String, OperatorQuery> queryMap;

    public OperatorResponseQuery getOperatorResponseQuery(OperatorRequestQuery operatorRequestQuery) {
        UUID uuid = UUID.randomUUID();
        String queryId = uuid.toString();
        String playerId = operatorRequestQuery.getPlayerId();
        String apiName = operatorRequestQuery.getApiName();
        String queryType = operatorRequestQuery.getQueryType();
        String requestJson = operatorRequestQuery.getRequestJson();
        OperatorRequestWithQueryIdQuery operatorRequestWithQueryIdQuery = OperatorRequestWithQueryIdQuery
                .builder()
                .queryId(queryId)
                .playerId(playerId)
                .apiName(apiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
        queryMap.put(queryId, operatorRequestWithQueryIdQuery);
        String responseJson = waitForResponseAndReturnResponseJson(queryId);
        queryMap.remove(queryId);
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public OperatorResponseQuery getOperatorResponseQuery(OperatorResponseQuery operatorResponseQuery) {
        String queryId = operatorResponseQuery.getQueryId();
        String responseJson;
        queryMap.replace(queryId, operatorResponseQuery);
        responseJson = waitForResponseAndReturnResponseJson(queryId);
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public int[] getOpenPorts() {
        return null;
    }

    public void setPort(int port) {}

    public int openAndReturnUnusedPort() {
        return 0;
    }

    public String findAndReturnIPAddress() {
        return null;
    }

    private String waitForResponseAndReturnResponseJson(String queryId) {
        String responseJson = "{}";
        OperatorResponseQuery operatorResponseQuery = (OperatorResponseQuery)queryMap.get(queryId);
        String responseQueryId = operatorResponseQuery.getQueryId();
        String response = operatorResponseQuery.getResponseJson();
        if (!response.equals("{}") && responseQueryId.equals(queryId))
            responseJson = response;
        return responseJson;
    }
}