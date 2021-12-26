package com.ebgolden.persistence.operatorservice;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ResponseQueryResponse {
    String queryId;
    String responseJson;
}