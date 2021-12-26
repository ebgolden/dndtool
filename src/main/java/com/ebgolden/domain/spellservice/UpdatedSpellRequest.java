package com.ebgolden.domain.spellservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Player;
import com.ebgolden.common.Spell;

@Builder
@Data
public class UpdatedSpellRequest {
    Spell spell;
    Player player;
}