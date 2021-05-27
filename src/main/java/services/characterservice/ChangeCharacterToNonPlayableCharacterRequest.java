package services.characterservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;
import commonobjects.DungeonMaster;

@Builder
@Value
public class ChangeCharacterToNonPlayableCharacterRequest {
    Character character;
    DungeonMaster dungeonMaster;
}