package services.characterdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.CharacterObject;
import objects.Player;

@Builder
@Value
public class CharacterDetailsVisibilityRequest {
    CharacterObject character;
    String visibilityJson;
    Player player;
}