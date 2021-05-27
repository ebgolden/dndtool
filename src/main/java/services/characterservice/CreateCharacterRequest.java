package services.characterservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;
import commonobjects.Player;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateCharacterRequest {
    Character character;
    Map<String, Visibility> visibilityMap;
    Player player;
}