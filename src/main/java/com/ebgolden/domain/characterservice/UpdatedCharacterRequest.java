package com.ebgolden.domain.characterservice;

import com.ebgolden.common.Character;
import com.ebgolden.common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedCharacterRequest {
    Character character;
    Player player;
}