package com.ebgolden.domain.characterservice;

import com.ebgolden.common.Character;
import com.ebgolden.common.Player;
import com.ebgolden.common.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfCharacterDetailsRequest {
    Character character;
    Map<String, Visibility> visibilityMap;
    Player player;
}