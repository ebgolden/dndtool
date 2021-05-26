package services.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.NonPlayableCharacter;

@Builder
@Value
public class NonPlayableCharacterBo {
    NonPlayableCharacter nonPlayableCharacter;
}