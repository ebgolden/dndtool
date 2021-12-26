package com.ebgolden.persistence.operatorservice;

import com.ebgolden.common.Campaign;
import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
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