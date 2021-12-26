package com.ebgolden.domain.characterservice.bll.bo;

import com.ebgolden.common.Character;
import com.ebgolden.common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterAndPlayerBo {
    Character character;
    Player player;
}