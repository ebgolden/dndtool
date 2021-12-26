package domain.spellservice;

import lombok.Builder;
import lombok.Data;
import common.Player;
import common.Spell;
import common.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfSpellDetailsRequest {
    Spell spell;
    Map<String, Visibility> visibilityMap;
    Player player;
}