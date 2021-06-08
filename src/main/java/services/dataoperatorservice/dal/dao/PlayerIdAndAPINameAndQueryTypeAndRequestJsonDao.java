package services.dataoperatorservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PlayerIdAndAPINameAndQueryTypeAndRequestJsonDao {
    String playerId;
    String apiName;
    String queryType;
    String requestJson;
}