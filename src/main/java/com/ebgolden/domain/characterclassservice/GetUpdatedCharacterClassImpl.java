package com.ebgolden.domain.characterclassservice;

import com.google.inject.Inject;
import com.ebgolden.domain.characterclassservice.bll.CharacterClassBusinessLogic;
import com.ebgolden.domain.characterclassservice.bll.CharacterClassBusinessLogicConverter;
import com.ebgolden.domain.characterclassservice.bll.bo.CharacterClassBo;

public class GetUpdatedCharacterClassImpl implements GetUpdatedCharacterClass {
    @Inject
    private CharacterClassBusinessLogicConverter characterClassBusinessLogicConverter;
    @Inject
    private CharacterClassBusinessLogic characterClassBusinessLogic;

    public UpdatedCharacterClassResponse getUpdatedCharacterClassResponse(UpdatedCharacterClassRequest updatedCharacterClassRequest) {
        CharacterClassBo characterClassBo = characterClassBusinessLogicConverter.getCharacterClassBoFromUpdatedCharacterClassRequest(updatedCharacterClassRequest);
        characterClassBo = characterClassBusinessLogic.getCharacterClassBo(characterClassBo);
        return characterClassBusinessLogicConverter.getUpdatedCharacterClassResponseFromCharacterClassBo(characterClassBo);
    }
}