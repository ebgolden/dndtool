package domain.actionservice;

import lombok.Builder;
import lombok.Value;
import common.Character;
import common.NonStandardAction;
import common.Player;

@Builder
@Value
public class ActionFromNonStandardActionRequest {
    NonStandardAction nonStandardAction;
    Character character;
    Player player;
}