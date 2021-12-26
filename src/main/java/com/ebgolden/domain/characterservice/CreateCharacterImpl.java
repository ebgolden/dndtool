package com.ebgolden.domain.characterservice;

import com.google.inject.Inject;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogic;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogicConverter;
import com.ebgolden.domain.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import com.ebgolden.domain.characterservice.bll.bo.CharacterBo;

public class CreateCharacterImpl implements CreateCharacter {
    @Inject
    private CharacterBusinessLogicConverter characterBusinessLogicConverter;
    @Inject
    private CharacterBusinessLogic characterBusinessLogic;

    public CreateCharacterResponse getCreateCharacterResponse(CreateCharacterRequest createCharacterRequest) {
        CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo = characterBusinessLogicConverter.getCharacterAndVisibilityAndPlayerBoFromCreateCharacterRequest(createCharacterRequest);
        CharacterBo characterBo = characterBusinessLogic.getCharacterBo(characterAndVisibilityAndPlayerBo);
        return characterBusinessLogicConverter.getCreateCharacterResponseFromCharacterBo(characterBo);
    }
}