package domain.characterservice;

import lombok.Builder;
import lombok.Value;
import common.NonPlayableCharacter;

@Builder
@Value
public class ChangeCharacterToNonPlayableCharacterResponse {
    NonPlayableCharacter nonPlayableCharacter;
}