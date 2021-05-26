package services.characterservice;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.DungeonMaster;

@Builder
@Value
public class ChangeCharacterToNonPlayableCharacterRequest {
    Character character;
    DungeonMaster dungeonMaster;
}