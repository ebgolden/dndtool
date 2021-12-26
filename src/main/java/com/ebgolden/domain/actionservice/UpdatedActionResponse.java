package com.ebgolden.domain.actionservice;

import com.ebgolden.common.Action;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedActionResponse {
    Action action;
}