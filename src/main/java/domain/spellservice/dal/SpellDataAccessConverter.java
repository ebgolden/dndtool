package domain.spellservice.dal;

import domain.spellservice.bll.bo.SpellAndPlayerBo;
import domain.spellservice.bll.bo.SpellAndVisibilityBo;
import domain.spellservice.dal.dao.SpellDao;
import domain.spellservice.dal.dao.SpellAndVisibilityDao;

public interface SpellDataAccessConverter {
    SpellDao getSpellDaoFromSpellAndPlayerBo(SpellAndPlayerBo spellAndPlayerBo);

    SpellAndVisibilityDao getSpellAndVisibilityDaoFromSpellAndVisibilityBo(SpellAndVisibilityBo spellAndVisibilityBo);

    SpellAndVisibilityBo getSpellAndVisibilityBoFromSpellAndVisibilityDao(SpellAndVisibilityDao spellAndVisibilityDao);

    SpellAndVisibilityDao getSpellAndVisibilityDaoFromSpellAndVisibilityJson(String spellAndVisibilityJson);
}