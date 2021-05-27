package services.characterservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.DungeonMaster;
import commonobjects.NonPlayableCharacter;

@Builder
@Value
public class ChangeNonPlayableCharacterToCharacterRequest {
    NonPlayableCharacter nonPlayableCharacter;
    DungeonMaster dungeonMaster;
}