package com.ebgolden.common;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class OperatorRequestQuery implements OperatorQuery {
    String campaignId;
    String playerId;
    String apiName;
    String queryType;
    String requestJson;
}