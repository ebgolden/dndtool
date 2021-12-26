package com.ebgolden.domain.characterclassservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.CharacterClass;

@Builder
@Value
public class UpdatedCharacterClassResponse {
    CharacterClass characterClass;
}