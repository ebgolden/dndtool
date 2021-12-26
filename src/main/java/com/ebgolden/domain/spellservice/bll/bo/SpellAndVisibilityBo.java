package domain.spellservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import common.Spell;
import common.Visibility;
import java.util.Map;

@Builder
@Data
public class SpellAndVisibilityBo {
    Spell spell;
    Map<String, Visibility> visibilityMap;
}