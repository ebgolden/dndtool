package com.ebgolden.domain.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Action;
import com.ebgolden.common.Character;
import com.ebgolden.common.Die;
import com.ebgolden.common.Player;

@Builder
@Value
public class ActionAndDiceAndCharacterAndPlayerBo {
    Action action;
    Die[] dice;
    Character character;
    Player player;
}