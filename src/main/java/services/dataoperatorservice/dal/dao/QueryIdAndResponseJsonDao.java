package services.dataoperatorservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class QueryIdAndResponseJsonDao {
    String queryId;
    String responseJson;
}