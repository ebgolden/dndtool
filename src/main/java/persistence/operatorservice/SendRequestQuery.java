package persistence.operatorservice;

public interface SendRequestQuery {
    /**
     * Returns a RequestQueryResponse containing a queryId String and a responseJson String.
     * Accepts a Campaign object, a Player object, a api object, a QueryType object, and a
     * requestJson String in a RequestQueryRequest.
     * @param requestQueryRequest RequestQueryRequest containing Campaign object, Player
     *                            object, api object, QueryType object, and requestJson
     *                            String
     * @return RequestQueryResponse containing queryId String and responseJson String
     */
    RequestQueryResponse getRequestQueryResponse(RequestQueryRequest requestQueryRequest);
}