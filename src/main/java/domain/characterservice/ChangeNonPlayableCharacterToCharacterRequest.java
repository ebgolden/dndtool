package domain.characterservice;

import lombok.Builder;
import lombok.Value;
import common.DungeonMaster;
import common.NonPlayableCharacter;

@Builder
@Value
public class ChangeNonPlayableCharacterToCharacterRequest {
    NonPlayableCharacter nonPlayableCharacter;
    DungeonMaster dungeonMaster;
}