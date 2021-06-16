package application.characterclassreaderservice.dal;

import application.characterclassreaderservice.dal.dao.CharacterClassDao;
import application.characterclassreaderservice.dal.dao.CharacterClassNameDao;

public interface CharacterClassReaderDataAccess {
    CharacterClassDao getCharacterClassDao(CharacterClassNameDao characterClassNameDao);
}