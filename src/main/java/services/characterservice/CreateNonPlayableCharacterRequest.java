package services.characterservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.DungeonMaster;
import commonobjects.NonPlayableCharacter;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateNonPlayableCharacterRequest {
    NonPlayableCharacter nonPlayableCharacter;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}