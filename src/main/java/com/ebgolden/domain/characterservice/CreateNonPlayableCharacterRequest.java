package com.ebgolden.domain.characterservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.NonPlayableCharacter;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateNonPlayableCharacterRequest {
    NonPlayableCharacter nonPlayableCharacter;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}