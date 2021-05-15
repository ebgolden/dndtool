package services.characterdetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.CharacterObject;
import objects.Player;

@Builder
@Value
public class CharacterAndPlayerBo {
    CharacterObject character;
    Player player;
}