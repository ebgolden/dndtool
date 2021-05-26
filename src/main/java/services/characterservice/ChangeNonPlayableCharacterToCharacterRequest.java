package services.characterservice;

import lombok.Builder;
import lombok.Value;
import objects.DungeonMaster;
import objects.NonPlayableCharacter;

@Builder
@Value
public class ChangeNonPlayableCharacterToCharacterRequest {
    NonPlayableCharacter nonPlayableCharacter;
    DungeonMaster dungeonMaster;
}