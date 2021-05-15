package services.characterdetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.CharacterObject;
import objects.Player;

@Builder
@Value
public class CharacterDetailsAndVisibilityAndPlayerBo {
    CharacterObject character;
    String visibilityJson;
    Player player;
}