package com.ebgolden.domain.spellservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Player;
import com.ebgolden.common.Spell;

@Builder
@Data
public class SpellAndPlayerBo {
    Spell spell;
    Player player;
}