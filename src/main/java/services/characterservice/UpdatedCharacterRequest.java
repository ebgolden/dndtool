package services.characterservice;

import commonobjects.Character;
import commonobjects.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedCharacterRequest {
    Character character;
    Player player;
}