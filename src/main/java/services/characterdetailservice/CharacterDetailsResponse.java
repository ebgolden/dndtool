package services.characterdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Character;

@Builder
@Value
public class CharacterDetailsResponse {
    Character character;
}