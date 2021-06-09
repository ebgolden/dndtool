package domain.characterservice;

import lombok.Builder;
import lombok.Value;
import common.Character;
import common.Player;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class CreateCharacterRequest {
    Character character;
    Map<String, Visibility> visibilityMap;
    Player player;
}