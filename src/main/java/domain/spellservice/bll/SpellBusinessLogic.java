package domain.spellservice.bll;

import domain.spellservice.bll.bo.SpellAndPlayerBo;
import domain.spellservice.bll.bo.SpellAndVisibilityAndPlayerBo;
import domain.spellservice.bll.bo.SpellAndVisibilityBo;

public interface SpellBusinessLogic {
    SpellAndVisibilityBo getSpellAndVisibilityBo(SpellAndPlayerBo spellAndPlayerBo);

    SpellAndVisibilityBo getSpellAndVisibilityBo(SpellAndVisibilityAndPlayerBo spellAndVisibilityAndPlayerBo);
}