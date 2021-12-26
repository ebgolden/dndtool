package com.ebgolden.domain.actionservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Character;
import com.ebgolden.common.NonStandardAction;
import com.ebgolden.common.Player;

@Builder
@Value
public class ActionFromNonStandardActionRequest {
    NonStandardAction nonStandardAction;
    Character character;
    Player player;
    boolean approvedByDungeonMaster;
}