package com.ebgolden.domain.characterclassservice.dal;

import com.ebgolden.domain.characterclassservice.bll.bo.CharacterClassBo;
import com.ebgolden.domain.characterclassservice.dal.dao.CharacterClassDao;

public interface CharacterClassDataAccessConverter {
    CharacterClassDao getCharacterClassDaoFromCharacterClassBo(CharacterClassBo characterClassBo);

    CharacterClassBo getCharacterClassBoFromCharacterClassDao(CharacterClassDao characterClassDao);

    CharacterClassDao getCharacterClassDaoFromCharacterClassJson(String characterClassJson);
}