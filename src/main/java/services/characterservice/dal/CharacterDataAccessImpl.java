package services.characterservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.DataOperator;
import services.characterservice.dal.dao.*;

public class CharacterDataAccessImpl implements CharacterDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private CharacterDataAccessConverter characterDataAccessConverter;

    public CharacterDao getCharacterDao(CharacterAndVisibilityAndPlayerDao characterAndVisibilityDao) {
        String characterAndVisibilityAndPlayerJson = characterAndVisibilityDao.getCharacterAndVisibilityAndPlayerJson();
        dataOperator.sendRequestJson(api, characterAndVisibilityAndPlayerJson);
        String characterJson = dataOperator.getResponseJson();
        return characterDataAccessConverter.getCharacterDaoFromCharacterJson(characterJson);
    }

    public NonPlayableCharacterDao getNonPlayableCharacterDao(NonPlayableCharacterAndVisibilityAndDungeonMasterDao nonPlayableCharacterAndVisibilityAndDungeonMasterDao) {
        String nonPlayableCharacterAndVisibilityAndDungeonMasterJson = nonPlayableCharacterAndVisibilityAndDungeonMasterDao.getNonPlayableCharacterAndVisibilityAndDungeonMasterJson();
        dataOperator.sendRequestJson(api, nonPlayableCharacterAndVisibilityAndDungeonMasterJson);
        String nonPlayableCharacterJson = dataOperator.getResponseJson();
        return characterDataAccessConverter.getNonPlayableCharacterDaoFromNonPlayableCharacterJson(nonPlayableCharacterJson);
    }

    public NonPlayableCharacterDao getNonPlayableCharacterDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        dataOperator.sendRequestJson(api, characterJson);
        String nonPlayableCharacterJson = dataOperator.getResponseJson();
        return characterDataAccessConverter.getNonPlayableCharacterDaoFromNonPlayableCharacterJson(nonPlayableCharacterJson);
    }

    public CharacterDao getCharacterDao(NonPlayableCharacterDao nonPlayableCharacterDao) {
        String nonPlayableCharacterJson = nonPlayableCharacterDao.getNonPlayableCharacterJson();
        dataOperator.sendRequestJson(api, nonPlayableCharacterJson);
        String characterJson = dataOperator.getResponseJson();
        return characterDataAccessConverter.getCharacterDaoFromCharacterJson(characterJson);
    }

    public CharacterAndVisibilityDao getCharacterAndVisibilityDao(CharacterDao characterDao) {
        String characterJson = characterDao.getCharacterJson();
        dataOperator.sendRequestJson(api, characterJson);
        String characterAndVisibilityJson = dataOperator.getResponseJson();
        return characterDataAccessConverter.getCharacterAndVisibilityDaoFromCharacterAndVisibilityJson(characterAndVisibilityJson);
    }

    public CharacterAndVisibilityDao getCharacterAndVisibilityDao(CharacterAndVisibilityDao characterAndVisibilityDao) {
        String characterAndVisibilityJson = characterAndVisibilityDao.getCharacterAndVisibilityJson();
        dataOperator.sendRequestJson(api, characterAndVisibilityJson);
        characterAndVisibilityJson = dataOperator.getResponseJson();
        return characterDataAccessConverter.getCharacterAndVisibilityDaoFromCharacterAndVisibilityJson(characterAndVisibilityJson);
    }
}