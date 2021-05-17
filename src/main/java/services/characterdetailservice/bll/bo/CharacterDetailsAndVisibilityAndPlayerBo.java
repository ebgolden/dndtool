package services.characterdetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.Player;

@Builder
@Value
public class CharacterDetailsAndVisibilityAndPlayerBo {
    Character character;
    String visibilityJson;
    Player player;
}