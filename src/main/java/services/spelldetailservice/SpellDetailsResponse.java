package services.spelldetailservice;

import lombok.Builder;
import lombok.Data;
import objects.Spell;

@Builder
@Data
public class SpellDetailsResponse {
    Spell spell;
}