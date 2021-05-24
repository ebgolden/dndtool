package services.spelldetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.spelldetailservice.dal.dao.SpellDao;
import services.spelldetailservice.dal.dao.SpellDetailsAndVisibilityDao;

public class SpellDetailDataAccessImpl implements SpellDetailDataAccess {
    @Inject
    private SpellDetailDataAccessConverter spellDetailDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public SpellDetailsAndVisibilityDao getSpellDetailsAndVisibilityDao(SpellDao spellDao) {
        String spellJson = spellDao.getSpellJson();
        dataOperator.sendRequestJson(spellJson);
        String latestJsonObject = dataOperator.getResponseJson();
        return spellDetailDataAccessConverter.getSpellDetailsAndVisibilityDaoFromLatestJsonObject(latestJsonObject);
    }

    public SpellDetailsAndVisibilityDao getSpellDetailsAndVisibilityDao(SpellDetailsAndVisibilityDao spellDetailsAndVisibilityDao) {
        String spellDetailsAndVisibilityJson = spellDetailsAndVisibilityDao.getSpellDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(spellDetailsAndVisibilityJson);
        String updatedJsonObject = dataOperator.getResponseJson();
        return spellDetailDataAccessConverter.getSpellDetailsAndVisibilityDaoFromLatestJsonObject(updatedJsonObject);
    }
}