package com.ebgolden.domain.spellservice.bll;

import com.ebgolden.domain.spellservice.bll.bo.SpellAndPlayerBo;
import com.ebgolden.domain.spellservice.bll.bo.SpellAndVisibilityAndPlayerBo;
import com.ebgolden.domain.spellservice.bll.bo.SpellAndVisibilityBo;

public interface SpellBusinessLogic {
    SpellAndVisibilityBo getSpellAndVisibilityBo(SpellAndPlayerBo spellAndPlayerBo);

    SpellAndVisibilityBo getSpellAndVisibilityBo(SpellAndVisibilityAndPlayerBo spellAndVisibilityAndPlayerBo);
}