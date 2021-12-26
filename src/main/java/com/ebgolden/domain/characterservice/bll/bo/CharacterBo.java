package com.ebgolden.domain.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Character;

@Builder
@Value
public class CharacterBo {
    Character character;
}