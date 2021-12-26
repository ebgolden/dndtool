package com.ebgolden.domain.actionservice;

import com.ebgolden.common.Action;
import com.ebgolden.common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedActionRequest {
    Action action;
    Player player;
}