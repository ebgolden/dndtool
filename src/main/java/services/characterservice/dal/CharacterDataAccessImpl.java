package services.characterservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.characterservice.dal.dao.CharacterAndVisibilityAndPlayerDao;
import services.characterservice.dal.dao.CharacterDao;

public class CharacterDataAccessImpl implements CharacterDataAccess {
    @Inject
    private CharacterDataAccessConverter characterDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public CharacterDao getCharacterDao(CharacterAndVisibilityAndPlayerDao characterDetailsAndVisibilityDao) {
        String characterDetailsAndVisibilityJson = characterDetailsAndVisibilityDao.getCharacterAndVisibilityAndPlayerJson();
        dataOperator.sendRequestJson(characterDetailsAndVisibilityJson);
        String characterJsonObject = dataOperator.getResponseJson();
        return characterDataAccessConverter.getCharacterDaoFromCharacterJsonObject(characterJsonObject);
    }
}