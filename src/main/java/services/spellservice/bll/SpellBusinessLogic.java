package services.spellservice.bll;

import services.spellservice.bll.bo.SpellAndPlayerBo;
import services.spellservice.bll.bo.SpellAndVisibilityAndPlayerBo;
import services.spellservice.bll.bo.SpellAndVisibilityBo;

public interface SpellBusinessLogic {
    SpellAndVisibilityBo getSpellAndVisibilityBo(SpellAndPlayerBo spellAndPlayerBo);

    SpellAndVisibilityBo getSpellAndVisibilityBo(SpellAndVisibilityAndPlayerBo spellAndVisibilityAndPlayerBo);
}