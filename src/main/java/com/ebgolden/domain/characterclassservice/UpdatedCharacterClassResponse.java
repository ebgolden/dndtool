package domain.characterclassservice;

import lombok.Builder;
import lombok.Value;
import common.CharacterClass;

@Builder
@Value
public class UpdatedCharacterClassResponse {
    CharacterClass characterClass;
}