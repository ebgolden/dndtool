package commonobjects;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

public class GlobalNetworkOperator implements DataOperator {
    private final AmazonDynamoDB DYNAMO_DB_CLIENT;
    private final DynamoDB DYNAMO_DB;
    private String responseJson;

    public GlobalNetworkOperator() {
        DYNAMO_DB_CLIENT = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .build();
        DYNAMO_DB = new DynamoDB(DYNAMO_DB_CLIENT);
    }

    @Override
    public void sendRequestJson(Object api, String requestJson) {
        String apiName = api.toString().substring(api.toString().lastIndexOf('.') + 1);
        Table table = DYNAMO_DB.getTable("campaigns");
        PutItemOutcome putItemOutcome = table.putItem(new Item()
                .withPrimaryKey("id", "123")
                .withString("API", apiName)
                .withString("Class", "Certified Badass ;)"));
        responseJson = putItemOutcome.toString();
        DYNAMO_DB_CLIENT.shutdown();
    }

    @Override
    public String getResponseJson() {
        return responseJson;
    }
}
