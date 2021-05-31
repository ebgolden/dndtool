package services.dataoperatorservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ResponseQueryResponse {
    String queryId;
    String responseJson;
}