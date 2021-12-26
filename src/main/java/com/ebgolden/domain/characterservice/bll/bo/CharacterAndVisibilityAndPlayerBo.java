package domain.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Character;
import common.Player;
import common.Visibility;
import java.util.Map;

@Builder
@Value
public class CharacterAndVisibilityAndPlayerBo {
    Character character;
    Map<String, Visibility> visibilityMap;
    Player player;
}