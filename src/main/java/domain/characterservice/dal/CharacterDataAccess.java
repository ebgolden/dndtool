package domain.characterservice.dal;

import domain.characterservice.dal.dao.*;

public interface CharacterDataAccess {
    CharacterDao getCharacterDao(CharacterAndVisibilityAndPlayerDao characterAndVisibilityDao);

    NonPlayableCharacterDao getNonPlayableCharacterDao(NonPlayableCharacterAndVisibilityAndDungeonMasterDao nonPlayableCharacterAndVisibilityAndDungeonMasterDao);

    NonPlayableCharacterDao getNonPlayableCharacterDao(CharacterDao characterDao);

    CharacterDao getCharacterDao(NonPlayableCharacterDao nonPlayableCharacterDao);

    CharacterAndVisibilityDao getCharacterAndVisibilityDao(CharacterDao characterDao);

    CharacterAndVisibilityDao getCharacterAndVisibilityDao(CharacterAndVisibilityDao characterAndVisibilityDao);
}