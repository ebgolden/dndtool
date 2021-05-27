package commonobjects;

public interface DataOperator {
    void sendRequestJson(Object api, String requestJson);

    String getResponseJson();
}