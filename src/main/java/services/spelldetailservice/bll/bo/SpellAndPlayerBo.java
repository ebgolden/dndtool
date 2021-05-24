package services.spelldetailservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Player;
import objects.Spell;

@Builder
@Data
public class SpellAndPlayerBo {
    Spell spell;
    Player player;
}