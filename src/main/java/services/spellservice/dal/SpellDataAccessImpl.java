package services.spellservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.DataOperator;
import services.spellservice.dal.dao.SpellDao;
import services.spellservice.dal.dao.SpellAndVisibilityDao;

public class SpellDataAccessImpl implements SpellDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private SpellDataAccessConverter spellDataAccessConverter;

    public SpellAndVisibilityDao getSpellAndVisibilityDao(SpellDao spellDao) {
        String spellJson = spellDao.getSpellJson();
        dataOperator.sendRequestJson(api, spellJson);
        String spellAndVisibilityJson = dataOperator.getResponseJson();
        return spellDataAccessConverter.getSpellAndVisibilityDaoFromSpellAndVisibilityJson(spellAndVisibilityJson);
    }

    public SpellAndVisibilityDao getSpellAndVisibilityDao(SpellAndVisibilityDao spellAndVisibilityDao) {
        String spellAndVisibilityJson = spellAndVisibilityDao.getSpellAndVisibilityJson();
        dataOperator.sendRequestJson(api, spellAndVisibilityJson);
        spellAndVisibilityJson = dataOperator.getResponseJson();
        return spellDataAccessConverter.getSpellAndVisibilityDaoFromSpellAndVisibilityJson(spellAndVisibilityJson);
    }
}