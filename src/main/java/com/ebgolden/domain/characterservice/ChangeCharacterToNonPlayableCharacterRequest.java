package com.ebgolden.domain.characterservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Character;
import com.ebgolden.common.DungeonMaster;

@Builder
@Value
public class ChangeCharacterToNonPlayableCharacterRequest {
    Character character;
    DungeonMaster dungeonMaster;
}