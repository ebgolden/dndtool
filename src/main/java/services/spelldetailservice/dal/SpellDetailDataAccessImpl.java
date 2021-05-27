package services.spelldetailservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import objects.DataOperator;
import services.spelldetailservice.dal.dao.SpellDao;
import services.spelldetailservice.dal.dao.SpellDetailsAndVisibilityDao;

public class SpellDetailDataAccessImpl implements SpellDetailDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private SpellDetailDataAccessConverter spellDetailDataAccessConverter;

    public SpellDetailsAndVisibilityDao getSpellDetailsAndVisibilityDao(SpellDao spellDao) {
        String spellJson = spellDao.getSpellJson();
        dataOperator.sendRequestJson(api, spellJson);
        String spellDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return spellDetailDataAccessConverter.getSpellDetailsAndVisibilityDaoFromSpellDetailsAndVisibilityJson(spellDetailsAndVisibilityJson);
    }

    public SpellDetailsAndVisibilityDao getSpellDetailsAndVisibilityDao(SpellDetailsAndVisibilityDao spellDetailsAndVisibilityDao) {
        String spellAndVisibilityJson = spellDetailsAndVisibilityDao.getSpellDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(api, spellAndVisibilityJson);
        String spellDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return spellDetailDataAccessConverter.getSpellDetailsAndVisibilityDaoFromSpellDetailsAndVisibilityJson(spellDetailsAndVisibilityJson);
    }
}