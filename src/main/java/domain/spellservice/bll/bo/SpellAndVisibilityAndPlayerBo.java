package domain.spellservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import common.Player;
import common.Spell;
import common.Visibility;
import java.util.Map;

@Builder
@Data
public class SpellAndVisibilityAndPlayerBo {
    Spell spell;
    Map<String, Visibility> visibilityMap;
    Player player;
}