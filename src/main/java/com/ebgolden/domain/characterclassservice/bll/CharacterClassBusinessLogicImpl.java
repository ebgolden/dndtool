package com.ebgolden.domain.characterclassservice.bll;

import com.google.inject.Inject;
import com.ebgolden.domain.characterclassservice.bll.bo.CharacterClassBo;
import com.ebgolden.domain.characterclassservice.dal.CharacterClassDataAccess;
import com.ebgolden.domain.characterclassservice.dal.CharacterClassDataAccessConverter;
import com.ebgolden.domain.characterclassservice.dal.dao.CharacterClassDao;

public class CharacterClassBusinessLogicImpl implements CharacterClassBusinessLogic {
    @Inject
    private CharacterClassDataAccessConverter characterClassDataAccessConverter;
    @Inject
    private CharacterClassDataAccess characterClassDataAccess;

    public CharacterClassBo getCharacterClassBo(CharacterClassBo characterClassBo) {
        CharacterClassDao characterClassDao = characterClassDataAccessConverter.getCharacterClassDaoFromCharacterClassBo(characterClassBo);
        characterClassDao = characterClassDataAccess.getCharacterClassDao(characterClassDao);
        return characterClassDataAccessConverter.getCharacterClassBoFromCharacterClassDao(characterClassDao);
    }
}