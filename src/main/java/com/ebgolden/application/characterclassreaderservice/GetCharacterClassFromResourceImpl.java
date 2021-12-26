package com.ebgolden.application.characterclassreaderservice;

import com.ebgolden.application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogic;
import com.ebgolden.application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogicConverter;
import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassBo;
import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassNameBo;
import com.google.inject.Inject;

public class GetCharacterClassFromResourceImpl implements GetCharacterClassFromResource {
    @Inject
    private CharacterClassReaderBusinessLogicConverter characterClassReaderBusinessLogicConverter;
    @Inject
    private CharacterClassReaderBusinessLogic characterClassReaderBusinessLogic;

    public CharacterClassFromResourceResponse getCharacterClassFromResourceResponse(CharacterClassFromResourceRequest characterClassFromResourceRequest) {
        CharacterClassNameBo characterClassNameBo = characterClassReaderBusinessLogicConverter.getCharacterClassNameBoFromCharacterClassFromResourceRequest(characterClassFromResourceRequest);
        CharacterClassBo characterClassBo = characterClassReaderBusinessLogic.getCharacterClassBo(characterClassNameBo);
        return characterClassReaderBusinessLogicConverter.getCharacterClassFromResourceResponseFromCharacterClassBo(characterClassBo);
    }
}