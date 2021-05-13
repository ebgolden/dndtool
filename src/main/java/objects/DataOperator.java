package objects;

public interface DataOperator {
    void sendRequestJson(String requestJson);

    String getResponseJson();
}
