package services.spelldetailservice.dal;

import services.spelldetailservice.dal.dao.SpellDao;
import services.spelldetailservice.dal.dao.SpellDetailsAndVisibilityDao;

public interface SpellDetailDataAccess {
    SpellDetailsAndVisibilityDao getSpellDetailsAndVisibilityDao(SpellDao spellDao);

    SpellDetailsAndVisibilityDao getSpellDetailsAndVisibilityDao(SpellDetailsAndVisibilityDao spellDetailsAndVisibilityDao);
}