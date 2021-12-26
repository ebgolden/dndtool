package domain.spellservice.dal;

import domain.spellservice.dal.dao.SpellDao;
import domain.spellservice.dal.dao.SpellAndVisibilityDao;

public interface SpellDataAccess {
    SpellAndVisibilityDao getSpellAndVisibilityDao(SpellDao spellDao);

    SpellAndVisibilityDao getSpellAndVisibilityDao(SpellAndVisibilityDao spellAndVisibilityDao);
}