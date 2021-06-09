package domain.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Character;
import common.Player;

@Builder
@Value
public class CharacterAndPlayerBo {
    Character character;
    Player player;
}