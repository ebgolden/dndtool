package services.characterservice;

import commonobjects.Character;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedCharacterResponse {
    Character character;
}