package services.dataoperatorservice;

import commonobjects.Campaign;
import commonobjects.Player;
import commonobjects.QueryType;
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