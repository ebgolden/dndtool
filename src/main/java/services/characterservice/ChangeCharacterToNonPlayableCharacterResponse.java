package services.characterservice;

import lombok.Builder;
import lombok.Value;
import objects.NonPlayableCharacter;

@Builder
@Value
public class ChangeCharacterToNonPlayableCharacterResponse {
    NonPlayableCharacter nonPlayableCharacter;
}