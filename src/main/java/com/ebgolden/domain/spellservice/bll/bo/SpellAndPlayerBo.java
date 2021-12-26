package domain.spellservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import common.Player;
import common.Spell;

@Builder
@Data
public class SpellAndPlayerBo {
    Spell spell;
    Player player;
}