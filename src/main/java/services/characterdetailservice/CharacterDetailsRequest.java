package services.characterdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.CharacterObject;
import objects.Player;

@Builder
@Value
public class CharacterDetailsRequest {
    CharacterObject character;
    Player player;
}