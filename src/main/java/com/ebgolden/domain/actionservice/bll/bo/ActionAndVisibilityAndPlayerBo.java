package com.ebgolden.domain.actionservice.bll.bo;

import com.ebgolden.common.Action;
import com.ebgolden.common.Player;
import com.ebgolden.common.Visibility;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Builder
@Value
public class ActionAndVisibilityAndPlayerBo {
    Action action;
    Map<String, Visibility> visibilityMap;
    Player player;
}