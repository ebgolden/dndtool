package domain.characterclassservice;

import lombok.Builder;
import lombok.Value;
import common.CharacterClass;

@Builder
@Value
public class UpdatedCharacterClassRequest {
    CharacterClass characterClass;
}