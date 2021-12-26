package com.ebgolden.persistence.operatorservice.bll.bo;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class QueryIdAndResponseJsonBo {
    String queryId;
    String responseJson;
}