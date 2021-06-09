package persistence.operatorservice;

import common.Campaign;
import common.Player;
import common.QueryType;
import lombok.*;

@Builder
@Value
public class RequestQueryRequest {
    Campaign campaign;
    Player player;
    Object api;
    QueryType queryType;
    String requestJson;
}