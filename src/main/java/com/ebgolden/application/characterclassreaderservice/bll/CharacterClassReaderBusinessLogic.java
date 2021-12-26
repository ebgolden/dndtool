package com.ebgolden.application.characterclassreaderservice.bll;

import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassBo;
import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassNameBo;

public interface CharacterClassReaderBusinessLogic {
    CharacterClassBo getCharacterClassBo(CharacterClassNameBo characterClassNameBo);
}