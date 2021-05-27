package services.actionservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;
import commonobjects.NonStandardAction;
import commonobjects.Player;

@Builder
@Value
public class ActionFromNonStandardActionRequest {
    NonStandardAction nonStandardAction;
    Character character;
    Player player;
}