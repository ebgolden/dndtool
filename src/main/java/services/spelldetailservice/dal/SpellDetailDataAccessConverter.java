package services.spelldetailservice.dal;

import services.spelldetailservice.bll.bo.SpellAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityBo;
import services.spelldetailservice.dal.dao.SpellDao;
import services.spelldetailservice.dal.dao.SpellDetailsAndVisibilityDao;

public interface SpellDetailDataAccessConverter {
    SpellDao getSpellDaoFromSpellAndPlayerBo(SpellAndPlayerBo spellAndPlayerBo);

    SpellDetailsAndVisibilityDao getSpellDetailsAndVisibilityDaoFromSpellDetailsAndVisibilityBo(SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo);

    SpellDetailsAndVisibilityBo getSpellDetailsAndVisibilityBoFromSpellDetailsAndVisibilityDao(SpellDetailsAndVisibilityDao spellDetailsAndVisibilityDao);

    SpellDetailsAndVisibilityDao getSpellDetailsAndVisibilityDaoFromLatestJsonObject(String latestJsonObject);
}