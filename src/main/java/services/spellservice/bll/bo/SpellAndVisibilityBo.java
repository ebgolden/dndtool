package services.spellservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.Spell;
import commonobjects.Visibility;
import java.util.Map;

@Builder
@Data
public class SpellAndVisibilityBo {
    Spell spell;
    Map<String, Visibility> visibilityMap;
}