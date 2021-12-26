package com.ebgolden.domain.characterservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.NonPlayableCharacter;

@Builder
@Value
public class ChangeNonPlayableCharacterToCharacterRequest {
    NonPlayableCharacter nonPlayableCharacter;
    DungeonMaster dungeonMaster;
}