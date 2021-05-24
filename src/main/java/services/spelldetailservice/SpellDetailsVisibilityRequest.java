package services.spelldetailservice;

import lombok.Builder;
import lombok.Data;
import objects.Player;
import objects.Spell;
import objects.Visibility;
import java.util.Map;

@Builder
@Data
public class SpellDetailsVisibilityRequest {
    Spell spell;
    Map<String, Visibility> visibilityMap;
    Player player;
}