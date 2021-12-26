package domain.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.NonPlayableCharacter;

@Builder
@Value
public class NonPlayableCharacterBo {
    NonPlayableCharacter nonPlayableCharacter;
}