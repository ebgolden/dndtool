package commonobjects;

public interface DataOperator {
    DataOperatorResponseQuery getDataOperatorResponseQuery(DataOperatorRequestQuery dataOperatorRequestQuery);

    DataOperatorResponseQuery getDataOperatorResponseQuery(DataOperatorResponseQuery dataOperatorResponseQuery);

    int[] getOpenPorts();

    void setPort(int port);

    int openAndReturnUnusedPort();

    String findAndReturnIPAddress();
}