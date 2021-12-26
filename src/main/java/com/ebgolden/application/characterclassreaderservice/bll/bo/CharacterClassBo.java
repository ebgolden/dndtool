package com.ebgolden.application.characterclassreaderservice.bll.bo;

import com.ebgolden.common.CharacterClass;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterClassBo {
    CharacterClass characterClass;
}