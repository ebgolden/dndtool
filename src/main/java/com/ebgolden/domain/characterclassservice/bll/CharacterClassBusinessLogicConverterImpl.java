package com.ebgolden.domain.characterclassservice.bll;

import com.ebgolden.common.CharacterClass;
import com.ebgolden.domain.characterclassservice.UpdatedCharacterClassRequest;
import com.ebgolden.domain.characterclassservice.UpdatedCharacterClassResponse;
import com.ebgolden.domain.characterclassservice.bll.bo.CharacterClassBo;

public class CharacterClassBusinessLogicConverterImpl implements CharacterClassBusinessLogicConverter {
    public CharacterClassBo getCharacterClassBoFromUpdatedCharacterClassRequest(UpdatedCharacterClassRequest updatedCharacterClassRequest) {
        CharacterClass characterClass = updatedCharacterClassRequest.getCharacterClass();
        return CharacterClassBo
                .builder()
                .characterClass(characterClass)
                .build();
    }

    public UpdatedCharacterClassResponse getUpdatedCharacterClassResponseFromCharacterClassBo(CharacterClassBo characterClassBo) {
        CharacterClass characterClass = characterClassBo.getCharacterClass();
        return UpdatedCharacterClassResponse
                .builder()
                .characterClass(characterClass)
                .build();
    }
}