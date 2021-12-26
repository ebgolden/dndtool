package com.ebgolden.domain.characterclassservice.dal;

import com.ebgolden.domain.characterclassservice.dal.dao.CharacterClassDao;

public interface CharacterClassDataAccess {
    CharacterClassDao getCharacterClassDao(CharacterClassDao characterClassDao);
}