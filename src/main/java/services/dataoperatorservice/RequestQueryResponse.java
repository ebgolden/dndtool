package services.dataoperatorservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RequestQueryResponse {
    String queryId;
    String responseJson;
}