package objects;

public interface DataOperator<T> {
    void sendRequestJson(String requestJson);

    String getResponseJson();
}