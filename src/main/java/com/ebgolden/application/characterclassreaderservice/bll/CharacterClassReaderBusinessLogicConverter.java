package com.ebgolden.application.characterclassreaderservice.bll;

import com.ebgolden.application.characterclassreaderservice.CharacterClassFromResourceRequest;
import com.ebgolden.application.characterclassreaderservice.CharacterClassFromResourceResponse;
import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassBo;
import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassNameBo;

public interface CharacterClassReaderBusinessLogicConverter {
    CharacterClassNameBo getCharacterClassNameBoFromCharacterClassFromResourceRequest(CharacterClassFromResourceRequest characterClassFromResourceRequest);

    CharacterClassFromResourceResponse getCharacterClassFromResourceResponseFromCharacterClassBo(CharacterClassBo characterClassBo);
}