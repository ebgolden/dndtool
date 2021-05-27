package services.characterservice;

import commonobjects.Character;
import commonobjects.Player;
import commonobjects.Visibility;
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