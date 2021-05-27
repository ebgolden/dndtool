package services.spellservice.dal;

import services.spellservice.bll.bo.SpellAndPlayerBo;
import services.spellservice.bll.bo.SpellAndVisibilityBo;
import services.spellservice.dal.dao.SpellDao;
import services.spellservice.dal.dao.SpellAndVisibilityDao;

public interface SpellDataAccessConverter {
    SpellDao getSpellDaoFromSpellAndPlayerBo(SpellAndPlayerBo spellAndPlayerBo);

    SpellAndVisibilityDao getSpellAndVisibilityDaoFromSpellAndVisibilityBo(SpellAndVisibilityBo spellAndVisibilityBo);

    SpellAndVisibilityBo getSpellAndVisibilityBoFromSpellAndVisibilityDao(SpellAndVisibilityDao spellAndVisibilityDao);

    SpellAndVisibilityDao getSpellAndVisibilityDaoFromSpellAndVisibilityJson(String spellAndVisibilityJson);
}