package commonobjects;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.*;

public class SingleDeviceOperator implements DataOperator {
    @Inject
    @Named("queryMap")
    private Map<String, DataOperatorQuery> queryMap;

    public DataOperatorResponseQuery getDataOperatorResponseQuery(DataOperatorRequestQuery dataOperatorRequestQuery) {
        UUID uuid = UUID.randomUUID();
        String queryId = uuid.toString();
        String playerId = dataOperatorRequestQuery.getPlayerId();
        String apiName = dataOperatorRequestQuery.getApiName();
        String queryType = dataOperatorRequestQuery.getQueryType();
        String requestJson = dataOperatorRequestQuery.getRequestJson();
        DataOperatorRequestWithQueryIdQuery dataOperatorRequestWithQueryIdQuery = DataOperatorRequestWithQueryIdQuery
                .builder()
                .queryId(queryId)
                .playerId(playerId)
                .apiName(apiName)
                .queryType(queryType)
                .requestJson(requestJson)
                .build();
        queryMap.put(queryId, dataOperatorRequestWithQueryIdQuery);
        String responseJson = waitForResponseAndReturnResponseJson(queryId);
        queryMap.remove(queryId);
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public DataOperatorResponseQuery getDataOperatorResponseQuery(DataOperatorResponseQuery dataOperatorResponseQuery) {
        String queryId = dataOperatorResponseQuery.getQueryId();
        String responseJson;
        queryMap.replace(queryId, dataOperatorResponseQuery);
        responseJson = waitForResponseAndReturnResponseJson(queryId);
        return DataOperatorResponseQuery
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
        DataOperatorResponseQuery dataOperatorResponseQuery = (DataOperatorResponseQuery)queryMap.get(queryId);
        String responseQueryId = dataOperatorResponseQuery.getQueryId();
        String response = dataOperatorResponseQuery.getResponseJson();
        if (!response.equals("{}") && responseQueryId.equals(queryId))
            responseJson = response;
        return responseJson;
    }
}