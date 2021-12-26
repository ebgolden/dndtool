package common;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class GlobalNetworkOperator implements Operator {
    private final Table TABLE;
    @Inject
    @Named("region")
    private Regions region;
    @Inject
    @Named("tableName")
    private String tableName;
    @Inject
    @Named("retries")
    private int retries;
    @Inject
    @Named("timeout")
    private int timeout;

    public GlobalNetworkOperator() {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withRegion(region)
                .build();
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        TABLE = dynamoDB.getTable(tableName);
    }

    public OperatorResponseQuery getOperatorResponseQuery(OperatorRequestQuery operatorRequestQuery) {
        UUID uuid = UUID.randomUUID();
        String queryId = uuid.toString();
        String campaignId = operatorRequestQuery.getCampaignId();
        String playerId = operatorRequestQuery.getPlayerId();
        String apiName = operatorRequestQuery.getApiName();
        String queryType = operatorRequestQuery.getQueryType();
        String requestJson = operatorRequestQuery.getRequestJson();
        TABLE.putItem(new Item()
                .withPrimaryKey("id", queryId)
                .withString("campaignId", campaignId)
                .withString("playerId", playerId)
                .withString("api", apiName)
                .withString("queryType", queryType)
                .withString("request", requestJson)
                .withString("response", "{}")
                .withLong("ttl", System.currentTimeMillis() / 1000L + 60));
        String responseJson = waitForResponseAndReturnResponseJson(queryId);
        return OperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public OperatorResponseQuery getOperatorResponseQuery(OperatorResponseQuery operatorResponseQuery) {
        String queryId = operatorResponseQuery.getQueryId();
        String responseJson = operatorResponseQuery.getResponseJson();
        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                .withPrimaryKey("id", queryId)
                .withUpdateExpression("set response=:r")
                .withValueMap(new ValueMap().withString(":r", responseJson))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        try {
            TABLE.updateItem(updateItemSpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        int interval = timeout / retries;
        int currentAttempt = 0;
        try {
            while (currentAttempt < retries) {
                Item item = TABLE.getItem("id", queryId);
                String response = item.get("response").toString();
                if (!response.equals("{}")) {
                    responseJson = response;
                    break;
                }
                else {
                    ++currentAttempt;
                    TimeUnit.MILLISECONDS.sleep(interval);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return responseJson;
    }
}