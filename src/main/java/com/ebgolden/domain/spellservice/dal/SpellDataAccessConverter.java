package com.ebgolden.domain.spellservice.dal;

import com.ebgolden.domain.spellservice.bll.bo.SpellAndPlayerBo;
import com.ebgolden.domain.spellservice.bll.bo.SpellAndVisibilityBo;
import com.ebgolden.domain.spellservice.dal.dao.SpellDao;
import com.ebgolden.domain.spellservice.dal.dao.SpellAndVisibilityDao;

public interface SpellDataAccessConverter {
    SpellDao getSpellDaoFromSpellAndPlayerBo(SpellAndPlayerBo spellAndPlayerBo);

    SpellAndVisibilityDao getSpellAndVisibilityDaoFromSpellAndVisibilityBo(SpellAndVisibilityBo spellAndVisibilityBo);

    SpellAndVisibilityBo getSpellAndVisibilityBoFromSpellAndVisibilityDao(SpellAndVisibilityDao spellAndVisibilityDao);

    SpellAndVisibilityDao getSpellAndVisibilityDaoFromSpellAndVisibilityJson(String spellAndVisibilityJson);
}