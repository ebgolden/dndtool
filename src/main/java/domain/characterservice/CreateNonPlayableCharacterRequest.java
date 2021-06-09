package domain.characterservice;

import lombok.Builder;
import lombok.Value;
import common.DungeonMaster;
import common.NonPlayableCharacter;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateNonPlayableCharacterRequest {
    NonPlayableCharacter nonPlayableCharacter;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}