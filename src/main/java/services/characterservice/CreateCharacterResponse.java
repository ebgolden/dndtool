package services.characterservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Character;

@Builder
@Value
public class CreateCharacterResponse {
    Character character;
}