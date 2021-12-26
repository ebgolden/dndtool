package com.ebgolden.domain.characterservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Character;

@Builder
@Value
public class ChangeNonPlayableCharacterToCharacterResponse {
    Character character;
}