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

    public CharacterDao getCharacterDao(CharacterAndVisibilityAndPlayerDao characterAndVisibilityDao) {
        String characterAndVisibilityAndPlayerJson = characterAndVisibilityDao.getCharacterAndVisibilityAndPlayerJson();
        dataOperator.sendRequestJson(characterAndVisibilityAndPlayerJson);
        String characterJsonObject = dataOperator.getResponseJson();
        return characterDataAccessConverter.getCharacterDaoFromCharacterJsonObject(characterJsonObject);
    }
}