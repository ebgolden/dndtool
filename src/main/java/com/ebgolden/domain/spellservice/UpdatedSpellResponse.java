package com.ebgolden.domain.spellservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Spell;

@Builder
@Data
public class UpdatedSpellResponse {
    Spell spell;
}