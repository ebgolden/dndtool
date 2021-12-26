package com.ebgolden.domain.actionservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Result;

@Builder
@Value
public class TakeActionResponse {
    Result result;
}