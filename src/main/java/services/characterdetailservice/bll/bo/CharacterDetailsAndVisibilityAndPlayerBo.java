package services.characterdetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.Player;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class CharacterDetailsAndVisibilityAndPlayerBo {
    Character character;
    Map<String, Visibility> visibilityMap;
    Player player;
}