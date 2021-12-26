package com.ebgolden.domain.actionservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Action;

@Builder
@Value
public class ActionsResponse {
    Action[] actions;
}