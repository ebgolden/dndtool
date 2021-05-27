package services.spellservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Spell;

@Builder
@Data
public class UpdatedSpellResponse {
    Spell spell;
}