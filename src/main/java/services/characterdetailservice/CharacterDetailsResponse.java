package services.characterdetailservice;

import lombok.Builder;
import lombok.Value;
import objects.CharacterObject;

@Builder
@Value
public class CharacterDetailsResponse {
    CharacterObject character;
}