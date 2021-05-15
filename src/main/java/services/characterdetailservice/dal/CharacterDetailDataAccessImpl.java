package services.characterdetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.characterdetailservice.dal.dao.CharacterDao;
import services.characterdetailservice.dal.dao.CharacterDetailsAndVisibilityDao;

public class CharacterDetailDataAccessImpl implements CharacterDetailDataAccess {
    @Inject
    private CharacterDetailDataAccessConverter characterDetailDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public CharacterDetailsAndVisibilityDao getCharacterDetailsAndVisibilityDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        dataOperator.sendRequestJson(characterJson);
        String latestJsonObject = dataOperator.getResponseJson();
        return characterDetailDataAccessConverter.getCharacterDetailsAndVisibilityDaoFromLatestJsonObject(latestJsonObject);
    }
}