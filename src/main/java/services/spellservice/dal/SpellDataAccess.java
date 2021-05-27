package services.spellservice.dal;

import services.spellservice.dal.dao.SpellDao;
import services.spellservice.dal.dao.SpellAndVisibilityDao;

public interface SpellDataAccess {
    SpellAndVisibilityDao getSpellAndVisibilityDao(SpellDao spellDao);

    SpellAndVisibilityDao getSpellAndVisibilityDao(SpellAndVisibilityDao spellAndVisibilityDao);
}