package com.ebgolden.domain.characterservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Character;
import com.ebgolden.common.Player;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Value
public class CharacterAndVisibilityAndPlayerBo {
    Character character;
    Map<String, Visibility> visibilityMap;
    Player player;
}