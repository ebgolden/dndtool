package com.ebgolden.application.characterclassreaderservice;

import com.ebgolden.common.CharacterClass;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterClassFromResourceResponse {
    CharacterClass characterClass;
}