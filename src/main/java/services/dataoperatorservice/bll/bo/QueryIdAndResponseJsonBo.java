package services.dataoperatorservice.bll.bo;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class QueryIdAndResponseJsonBo {
    String queryId;
    String responseJson;
}