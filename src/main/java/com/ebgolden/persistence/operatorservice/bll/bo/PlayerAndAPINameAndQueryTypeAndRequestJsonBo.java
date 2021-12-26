package com.ebgolden.persistence.operatorservice.bll.bo;

import com.ebgolden.common.Player;
import com.ebgolden.common.QueryType;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PlayerAndAPINameAndQueryTypeAndRequestJsonBo {
    Player player;
    String apiName;
    QueryType queryType;
    String requestJson;
}