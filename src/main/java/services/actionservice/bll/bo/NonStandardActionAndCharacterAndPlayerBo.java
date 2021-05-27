package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;
import commonobjects.NonStandardAction;
import commonobjects.Player;

@Builder
@Value
public class NonStandardActionAndCharacterAndPlayerBo {
    NonStandardAction nonStandardAction;
    Character character;
    Player player;
}