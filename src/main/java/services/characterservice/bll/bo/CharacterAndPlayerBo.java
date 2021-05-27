package services.characterservice.bll.bo;

import commonobjects.Character;
import commonobjects.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterAndPlayerBo {
    Character character;
    Player player;
}