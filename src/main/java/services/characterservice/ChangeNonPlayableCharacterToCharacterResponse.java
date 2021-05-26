package services.characterservice;

import lombok.Builder;
import lombok.Value;
import objects.Character;

@Builder
@Value
public class ChangeNonPlayableCharacterToCharacterResponse {
    Character character;
}