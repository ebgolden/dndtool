package services.dataoperatorservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class QueryResponse {
    String queryId;
    String responseJson;
}