package com.ebgolden.application.characterclassreaderservice.bll;

import com.ebgolden.application.characterclassreaderservice.CharacterClassFromResourceRequest;
import com.ebgolden.application.characterclassreaderservice.CharacterClassFromResourceResponse;
import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassBo;
import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassNameBo;
import com.ebgolden.common.CharacterClass;

public class CharacterClassReaderBusinessLogicConverterImpl implements CharacterClassReaderBusinessLogicConverter {
    public CharacterClassNameBo getCharacterClassNameBoFromCharacterClassFromResourceRequest(CharacterClassFromResourceRequest characterClassFromResourceRequest) {
        String characterClassName = characterClassFromResourceRequest.getCharacterClassName();
        return CharacterClassNameBo
                .builder()
                .characterClassName(characterClassName)
                .build();
    }

    public CharacterClassFromResourceResponse getCharacterClassFromResourceResponseFromCharacterClassBo(CharacterClassBo characterClassBo) {
        CharacterClass characterClass = characterClassBo.getCharacterClass();
        return CharacterClassFromResourceResponse
                .builder()
                .characterClass(characterClass)
                .build();
    }
}