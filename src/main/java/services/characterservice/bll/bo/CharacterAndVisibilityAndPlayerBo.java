package services.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;
import commonobjects.Player;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Value
public class CharacterAndVisibilityAndPlayerBo {
    Character character;
    Map<String, Visibility> visibilityMap;
    Player player;
}