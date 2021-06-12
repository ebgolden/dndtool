package domain.characterclassservice.dal;

import domain.characterclassservice.dal.dao.CharacterClassDao;

public interface CharacterClassDataAccess {
    CharacterClassDao getCharacterClassDao(CharacterClassDao characterClassDao);
}