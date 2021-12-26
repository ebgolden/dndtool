package domain.spellservice;

import lombok.Builder;
import lombok.Data;
import common.Player;
import common.Spell;

@Builder
@Data
public class UpdatedSpellRequest {
    Spell spell;
    Player player;
}