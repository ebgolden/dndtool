package services.characterservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.NonPlayableCharacter;

@Builder
@Value
public class ChangeCharacterToNonPlayableCharacterResponse {
    NonPlayableCharacter nonPlayableCharacter;
}