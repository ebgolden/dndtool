package com.ebgolden.application.characterclassreaderservice.dal;

import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassBo;
import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassNameBo;
import com.ebgolden.application.characterclassreaderservice.dal.dao.CharacterClassDao;
import com.ebgolden.application.characterclassreaderservice.dal.dao.CharacterClassNameDao;

public interface CharacterClassReaderDataAccessConverter {
    CharacterClassNameDao getCharacterClassNameDaoFromCharacterClassNameBo(CharacterClassNameBo characterClassNameBo);

    CharacterClassBo getCharacterClassBoFromCharacterClassDao(CharacterClassDao characterClassDao);

    CharacterClassDao getCharacterClassDaoFromCharacterClassJson(String characterClassJson);
}