package com.ebgolden.domain.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Character;
import com.ebgolden.common.Player;

@Builder
@Value
public class CharacterAndPlayerBo {
    Character character;
    Player player;
}