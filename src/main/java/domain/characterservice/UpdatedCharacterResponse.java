package domain.characterservice;

import common.Character;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedCharacterResponse {
    Character character;
}