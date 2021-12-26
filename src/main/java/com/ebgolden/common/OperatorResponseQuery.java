package com.ebgolden.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OperatorResponseQuery implements OperatorQuery {
    String queryId;
    String responseJson;
}