package services.spellservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Player;
import commonobjects.Spell;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Data
public class ChangeVisibilityOfSpellDetailsRequest {
    Spell spell;
    Map<String, Visibility> visibilityMap;
    Player player;
}