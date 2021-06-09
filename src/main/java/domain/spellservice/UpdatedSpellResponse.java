package domain.spellservice;

import lombok.Builder;
import lombok.Data;
import common.Spell;

@Builder
@Data
public class UpdatedSpellResponse {
    Spell spell;
}