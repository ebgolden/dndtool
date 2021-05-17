package services.characterdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.Player;

@Builder
@Value
public class CharacterDetailsVisibilityRequest {
    Character character;
    String visibilityJson;
    Player player;
}