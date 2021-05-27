package services.spellservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.Player;
import commonobjects.Spell;

@Builder
@Data
public class SpellAndPlayerBo {
    Spell spell;
    Player player;
}