package services.actionservice;

import lombok.Builder;
import lombok.Value;
import objects.Character;
import objects.NonStandardAction;
import objects.Player;

@Builder
@Value
public class ActionFromNonStandardActionRequest {
    NonStandardAction nonStandardAction;
    Character character;
    Player player;
}