package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;
import commonobjects.Player;

@Builder
@Value
public class CharacterAndPlayerBo {
    Character character;
    Player player;
}