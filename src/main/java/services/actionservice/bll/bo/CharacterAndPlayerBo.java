package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.Player;

@Builder
@Value
public class CharacterAndPlayerBo {
    Character character;
    Player player;
}