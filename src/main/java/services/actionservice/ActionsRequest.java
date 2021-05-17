package services.actionservice;

import lombok.Builder;
import lombok.Value;
import objects.Character;

@Builder
@Value
public class ActionsRequest {
    Character character;
}