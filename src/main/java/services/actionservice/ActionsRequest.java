package services.actionservice;

import lombok.Builder;
import lombok.Value;
import objects.CharacterObject;

@Builder
@Value
public class ActionsRequest {
    CharacterObject character;
}