package services.turnqueueservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class TurnQueueDao {
    String turnQueueJson;
}