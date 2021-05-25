package services.characterservice;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.Player;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateCharacterRequest {
    Character character;
    Map<String, Visibility> visibilityMap;
    Player player;
}