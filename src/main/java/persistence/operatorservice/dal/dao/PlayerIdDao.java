package persistence.operatorservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PlayerIdDao {
    String playerId;
}