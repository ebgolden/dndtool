package services.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.NonStandardAction;
import objects.Player;

@Builder
@Value
public class NonStandardActionAndCharacterAndPlayerBo {
    NonStandardAction nonStandardAction;
    Character character;
    Player player;
}