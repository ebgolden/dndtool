package services.characterservice.dal;

import services.characterservice.dal.dao.CharacterAndVisibilityAndPlayerDao;
import services.characterservice.dal.dao.CharacterDao;
import services.characterservice.dal.dao.NonPlayableCharacterAndVisibilityAndDungeonMasterDao;
import services.characterservice.dal.dao.NonPlayableCharacterDao;

public interface CharacterDataAccess {
    CharacterDao getCharacterDao(CharacterAndVisibilityAndPlayerDao characterAndVisibilityDao);

    NonPlayableCharacterDao getNonPlayableCharacterDao(NonPlayableCharacterAndVisibilityAndDungeonMasterDao nonPlayableCharacterAndVisibilityAndDungeonMasterDao);

    NonPlayableCharacterDao getNonPlayableCharacterDao(CharacterDao characterDao);
}