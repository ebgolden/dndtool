package services.spelldetailservice;

import lombok.Builder;
import lombok.Data;
import objects.Player;
import objects.Spell;

@Builder
@Data
public class SpellDetailsRequest {
    Spell spell;
    Player player;
}