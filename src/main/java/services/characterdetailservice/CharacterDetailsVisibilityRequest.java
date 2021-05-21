package services.characterdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.Player;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class CharacterDetailsVisibilityRequest {
    Character character;
    Map<String, Visibility> visibilityMap;
    Player player;
}