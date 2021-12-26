package com.ebgolden.domain.characterservice;

import com.ebgolden.common.Character;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UpdatedCharacterResponse {
    Character character;
}