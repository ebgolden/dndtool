package com.ebgolden.domain.characterservice;

import com.google.inject.Inject;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogic;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogicConverter;
import com.ebgolden.domain.characterservice.bll.bo.CharacterAndPlayerBo;
import com.ebgolden.domain.characterservice.bll.bo.CharacterAndVisibilityBo;

public class GetUpdatedCharacterImpl implements GetUpdatedCharacter {
    @Inject
    private CharacterBusinessLogicConverter characterBusinessLogicConverter;
    @Inject
    private CharacterBusinessLogic characterBusinessLogic;

    public UpdatedCharacterResponse getUpdatedCharacterResponse(UpdatedCharacterRequest updatedCharacterRequest) {
        CharacterAndPlayerBo characterAndPlayerBo = characterBusinessLogicConverter.getCharacterAndPlayerBoFromUpdatedCharacterRequest(updatedCharacterRequest);
        CharacterAndVisibilityBo characterAndVisibilityBo = characterBusinessLogic.getCharacterAndVisibilityBo(characterAndPlayerBo);
        return characterBusinessLogicConverter.getUpdatedCharacterResponseFromCharacterBo(characterAndVisibilityBo);
    }
}