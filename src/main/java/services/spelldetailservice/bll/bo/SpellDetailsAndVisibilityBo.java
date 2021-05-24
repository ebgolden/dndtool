package services.spelldetailservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Spell;
import objects.Visibility;
import java.util.Map;

@Builder
@Data
public class SpellDetailsAndVisibilityBo {
    Spell spell;
    Map<String, Visibility> visibilityMap;
}