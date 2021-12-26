package com.ebgolden.domain.spellservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Player;
import com.ebgolden.common.Spell;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfSpellDetailsRequest {
    Spell spell;
    Map<String, Visibility> visibilityMap;
    Player player;
}