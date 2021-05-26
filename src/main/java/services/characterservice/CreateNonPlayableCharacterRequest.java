package services.characterservice;

import lombok.Builder;
import lombok.Value;
import objects.DungeonMaster;
import objects.NonPlayableCharacter;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateNonPlayableCharacterRequest {
    NonPlayableCharacter nonPlayableCharacter;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}