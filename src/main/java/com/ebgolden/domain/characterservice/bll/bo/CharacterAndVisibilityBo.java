package com.ebgolden.domain.characterservice.bll.bo;

import com.ebgolden.common.Character;
import com.ebgolden.common.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class CharacterAndVisibilityBo {
    Character character;
    Map<String, Visibility> visibilityMap;
}