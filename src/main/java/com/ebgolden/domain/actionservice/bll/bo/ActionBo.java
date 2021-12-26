package com.ebgolden.domain.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Action;

@Builder
@Value
public class ActionBo {
    Action action;
}