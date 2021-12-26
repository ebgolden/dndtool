package domain.characterservice;

import common.Character;
import common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedCharacterRequest {
    Character character;
    Player player;
}