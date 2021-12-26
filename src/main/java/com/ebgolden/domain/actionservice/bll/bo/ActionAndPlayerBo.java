package com.ebgolden.domain.actionservice.bll.bo;

import com.ebgolden.common.Action;
import com.ebgolden.common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ActionAndPlayerBo {
    Action action;
    Player player;
}