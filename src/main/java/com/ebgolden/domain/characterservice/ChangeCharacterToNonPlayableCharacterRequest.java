package domain.characterservice;

import lombok.Builder;
import lombok.Value;
import common.Character;
import common.DungeonMaster;

@Builder
@Value
public class ChangeCharacterToNonPlayableCharacterRequest {
    Character character;
    DungeonMaster dungeonMaster;
}