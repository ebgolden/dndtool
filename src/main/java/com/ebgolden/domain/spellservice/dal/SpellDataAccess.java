package com.ebgolden.domain.spellservice.dal;

import com.ebgolden.domain.spellservice.dal.dao.SpellDao;
import com.ebgolden.domain.spellservice.dal.dao.SpellAndVisibilityDao;

public interface SpellDataAccess {
    SpellAndVisibilityDao getSpellAndVisibilityDao(SpellDao spellDao);

    SpellAndVisibilityDao getSpellAndVisibilityDao(SpellAndVisibilityDao spellAndVisibilityDao);
}