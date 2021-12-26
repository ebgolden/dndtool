package persistence.operatorservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ResponseQueryRequest {
    String queryId;
    String responseJson;
}