package domain.characterservice.bll.bo;

import common.Character;
import common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterAndPlayerBo {
    Character character;
    Player player;
}