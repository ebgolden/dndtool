package services.dataoperatorservice;

public interface SendResponseQuery {
    /**
     * Returns a ResponseQueryResponse containing a queryId String and a responseJson String.
     * Accepts a queryId String and a responseJson String in a ResponseQueryRequest.
     * @param responseQueryRequest ResponseQueryRequest containing queryId String and
     *                             responseJson String
     * @return ResponseQueryResponse containing queryId String and responseJson String
     */
    ResponseQueryResponse getResponseQueryResponse(ResponseQueryRequest responseQueryRequest);
}