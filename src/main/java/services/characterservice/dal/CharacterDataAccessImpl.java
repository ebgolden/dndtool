package services.characterservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.characterservice.dal.dao.CharacterAndVisibilityAndPlayerDao;
import services.characterservice.dal.dao.CharacterDao;
import services.characterservice.dal.dao.NonPlayableCharacterAndVisibilityAndDungeonMasterDao;
import services.characterservice.dal.dao.NonPlayableCharacterDao;

public class CharacterDataAccessImpl implements CharacterDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private CharacterDataAccessConverter characterDataAccessConverter;

    public CharacterDao getCharacterDao(CharacterAndVisibilityAndPlayerDao characterAndVisibilityDao) {
        String characterAndVisibilityAndPlayerJson = characterAndVisibilityDao.getCharacterAndVisibilityAndPlayerJson();
        dataOperator.sendRequestJson(characterAndVisibilityAndPlayerJson);
        String characterJson = dataOperator.getResponseJson();
        return characterDataAccessConverter.getCharacterDaoFromCharacterJson(characterJson);
    }

    public NonPlayableCharacterDao getNonPlayableCharacterDao(NonPlayableCharacterAndVisibilityAndDungeonMasterDao nonPlayableCharacterAndVisibilityAndDungeonMasterDao) {
        String nonPlayableCharacterAndVisibilityAndDungeonMasterJson = nonPlayableCharacterAndVisibilityAndDungeonMasterDao.getNonPlayableCharacterAndVisibilityAndDungeonMasterJson();
        dataOperator.sendRequestJson(nonPlayableCharacterAndVisibilityAndDungeonMasterJson);
        String nonPlayableCharacterJson = dataOperator.getResponseJson();
        return characterDataAccessConverter.getNonPlayableCharacterDaoFromNonPlayableCharacterJson(nonPlayableCharacterJson);
    }

    public NonPlayableCharacterDao getNonPlayableCharacterDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        dataOperator.sendRequestJson(characterJson);
        String nonPlayableCharacterJson = dataOperator.getResponseJson();
        return characterDataAccessConverter.getNonPlayableCharacterDaoFromNonPlayableCharacterJson(nonPlayableCharacterJson);
    }
}