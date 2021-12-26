package com.ebgolden.application.characterclassreaderservice.dal;

import com.ebgolden.application.characterclassreaderservice.dal.dao.CharacterClassDao;
import com.ebgolden.application.characterclassreaderservice.dal.dao.CharacterClassNameDao;

public interface CharacterClassReaderDataAccess {
    CharacterClassDao getCharacterClassDao(CharacterClassNameDao characterClassNameDao);
}