package commonobjects;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import java.util.concurrent.TimeUnit;

public class GlobalNetworkOperator implements DataOperator {
    private final Table TABLE;

    public GlobalNetworkOperator() {
        AmazonDynamoDB DYNAMO_DB_CLIENT = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .build();
        DynamoDB dynamoDB = new DynamoDB(DYNAMO_DB_CLIENT);
        TABLE = dynamoDB.getTable("campaigntraffic");
    }

    public DataOperatorResponseQuery getResponseJson(DataOperatorRequestQuery dataOperatorRequestQuery) {
        String queryId = "123";
        String campaignId = dataOperatorRequestQuery.getCampaignId();
        String senderPlayerId = dataOperatorRequestQuery.getSenderPlayerId();
        String apiName = dataOperatorRequestQuery.getApiName();
        String queryType = dataOperatorRequestQuery.getQueryType();
        String requestJson = dataOperatorRequestQuery.getRequestJson();
        TABLE.putItem(new Item()
                .withPrimaryKey("id", queryId)
                .withString("campaignId", campaignId)
                .withString("senderPlayerId", senderPlayerId)
                .withString("api", apiName)
                .withString("queryType", queryType)
                .withString("request", requestJson)
                .withString("response", "{}")
                .withLong("ttl", System.currentTimeMillis() / 1000L + 60));
        String responseJson = waitForResponseAndReturnResponseJson(queryId);
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    public DataOperatorResponseQuery getResponseJson(DataOperatorResponseQuery dataOperatorResponseQuery) {
        String queryId = dataOperatorResponseQuery.getQueryId();
        String responseJson = dataOperatorResponseQuery.getResponseJson();
        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                .withPrimaryKey("id", queryId)
                .withUpdateExpression("set response=:r")
                .withValueMap(new ValueMap().withString(":r", responseJson))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        try {
            TABLE.updateItem(updateItemSpec);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        responseJson = waitForResponseAndReturnResponseJson(queryId);
        return DataOperatorResponseQuery
                .builder()
                .queryId(queryId)
                .responseJson(responseJson)
                .build();
    }

    private String waitForResponseAndReturnResponseJson(String queryId) {
        String responseJson = "{}";
        int interval = 15;
        int maxRetryAttempts = 4;
        int currentAttempt = 0;
        try {
            while (currentAttempt < maxRetryAttempts) {
                Item item = TABLE.getItem("id", queryId);
                String response = item.get("response").toString();
                if (!response.equals("{}")) {
                    responseJson = response;
                    break;
                }
                else {
                    ++currentAttempt;
                    TimeUnit.SECONDS.sleep(interval);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return responseJson;
    }
}