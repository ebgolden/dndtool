package services.characterdetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.characterdetailservice.dal.dao.CharacterDao;
import services.characterdetailservice.dal.dao.CharacterDetailsAndVisibilityDao;

public class CharacterDetailDataAccessImpl implements CharacterDetailDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private CharacterDetailDataAccessConverter characterDetailDataAccessConverter;

    public CharacterDetailsAndVisibilityDao getCharacterDetailsAndVisibilityDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        dataOperator.sendRequestJson(characterJson);
        String characterDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return characterDetailDataAccessConverter.getCharacterDetailsAndVisibilityDaoFromCharacterDetailsAndVisibilityJson(characterDetailsAndVisibilityJson);
    }

    public CharacterDetailsAndVisibilityDao getCharacterDetailsAndVisibilityDao(CharacterDetailsAndVisibilityDao characterDetailsAndVisibilityDao) {
        String characterAndVisibilityJson = characterDetailsAndVisibilityDao.getCharacterDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(characterAndVisibilityJson);
        String characterDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return characterDetailDataAccessConverter.getCharacterDetailsAndVisibilityDaoFromCharacterDetailsAndVisibilityJson(characterDetailsAndVisibilityJson);
    }
}