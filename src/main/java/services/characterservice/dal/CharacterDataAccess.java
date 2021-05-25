package services.characterservice.dal;

import services.characterservice.dal.dao.CharacterAndVisibilityAndPlayerDao;
import services.characterservice.dal.dao.CharacterDao;

public interface CharacterDataAccess {
    CharacterDao getCharacterDao(CharacterAndVisibilityAndPlayerDao characterAndVisibilityDao);
}