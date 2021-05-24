package services.spelldetailservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Player;
import objects.Spell;
import objects.Visibility;
import java.util.Map;

@Builder
@Data
public class SpellDetailsAndVisibilityAndPlayerBo {
    Spell spell;
    Map<String, Visibility> visibilityMap;
    Player player;
}