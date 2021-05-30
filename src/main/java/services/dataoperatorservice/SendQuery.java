package services.dataoperatorservice;

public interface SendQuery {
    /**
     * Returns a QueryResponse containing a queryId String and a responseJson String.
     * Accepts a Campaign object, a Player object, a QueryType object, a api
     * object, and a requestJson String in a QueryRequest.
     * @param queryRequest QueryRequest containing Campaign object, Player object,
     *                     QueryType object, api object, and requestJson String
     * @return QueryResponse containing queryId String and responseJson String
     */
    QueryResponse getQueryResponse(QueryRequest queryRequest);
}