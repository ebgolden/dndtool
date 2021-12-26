package com.ebgolden.domain.characterclassservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.CharacterClass;

@Builder
@Value
public class CharacterClassBo {
    CharacterClass characterClass;
}