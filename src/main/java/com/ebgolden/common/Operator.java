package common;

public interface Operator {
    OperatorResponseQuery getOperatorResponseQuery(OperatorRequestQuery operatorRequestQuery);

    OperatorResponseQuery getOperatorResponseQuery(OperatorResponseQuery operatorResponseQuery);

    int[] getOpenPorts();

    void setPort(int port);

    int openAndReturnUnusedPort();

    String findAndReturnIPAddress();
}