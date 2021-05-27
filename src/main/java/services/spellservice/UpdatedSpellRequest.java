package services.spellservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Player;
import commonobjects.Spell;

@Builder
@Data
public class UpdatedSpellRequest {
    Spell spell;
    Player player;
}