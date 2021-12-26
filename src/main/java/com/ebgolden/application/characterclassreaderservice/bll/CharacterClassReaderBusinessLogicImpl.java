package com.ebgolden.application.characterclassreaderservice.bll;

import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassBo;
import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassNameBo;
import com.ebgolden.application.characterclassreaderservice.dal.CharacterClassReaderDataAccess;
import com.ebgolden.application.characterclassreaderservice.dal.CharacterClassReaderDataAccessConverter;
import com.ebgolden.application.characterclassreaderservice.dal.dao.CharacterClassDao;
import com.ebgolden.application.characterclassreaderservice.dal.dao.CharacterClassNameDao;
import com.google.inject.Inject;

public class CharacterClassReaderBusinessLogicImpl implements CharacterClassReaderBusinessLogic {
    @Inject
    private CharacterClassReaderDataAccessConverter characterClassReaderDataAccessConverter;
    @Inject
    private CharacterClassReaderDataAccess characterClassReaderDataAccess;

    public CharacterClassBo getCharacterClassBo(CharacterClassNameBo characterClassNameBo) {
        CharacterClassNameDao characterClassNameDao = characterClassReaderDataAccessConverter.getCharacterClassNameDaoFromCharacterClassNameBo(characterClassNameBo);
        CharacterClassDao characterClassDao = characterClassReaderDataAccess.getCharacterClassDao(characterClassNameDao);
        if (characterClassDao.getCharacterClassJson().equals("{}"))
            return CharacterClassBo
                    .builder()
                    .build();
        return characterClassReaderDataAccessConverter.getCharacterClassBoFromCharacterClassDao(characterClassDao);
    }
}