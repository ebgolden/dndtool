package domain.characterservice;

import lombok.Builder;
import lombok.Value;
import common.Character;

@Builder
@Value
public class ChangeNonPlayableCharacterToCharacterResponse {
    Character character;
}