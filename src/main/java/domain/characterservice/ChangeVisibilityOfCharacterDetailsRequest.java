package domain.characterservice;

import common.Character;
import common.Player;
import common.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfCharacterDetailsRequest {
    Character character;
    Map<String, Visibility> visibilityMap;
    Player player;
}