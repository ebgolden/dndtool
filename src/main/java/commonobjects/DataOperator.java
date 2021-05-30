package commonobjects;

public interface DataOperator {
    DataOperatorResponseQuery getResponseJson(DataOperatorRequestQuery dataOperatorRequestQuery);

    DataOperatorResponseQuery getResponseJson(DataOperatorResponseQuery dataOperatorResponseQuery);
}