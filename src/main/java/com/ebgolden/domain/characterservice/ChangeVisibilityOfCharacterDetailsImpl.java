package com.ebgolden.domain.characterservice;

import com.google.inject.Inject;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogic;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogicConverter;
import com.ebgolden.domain.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import com.ebgolden.domain.characterservice.bll.bo.CharacterAndVisibilityBo;

public class ChangeVisibilityOfCharacterDetailsImpl implements ChangeVisibilityOfCharacterDetails {
    @Inject
    private CharacterBusinessLogicConverter characterBusinessLogicConverter;
    @Inject
    private CharacterBusinessLogic characterBusinessLogic;

    public ChangeVisibilityOfCharacterDetailsResponse getChangeVisibilityOfCharacterDetailsResponse(ChangeVisibilityOfCharacterDetailsRequest changeVisibilityOfUpdatedCharacterRequest) {
        CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo = characterBusinessLogicConverter.getCharacterAndVisibilityAndPlayerBoFromChangeVisibilityOfCharacterDetailsRequest(changeVisibilityOfUpdatedCharacterRequest);
        CharacterAndVisibilityBo characterAndVisibilityBo = characterBusinessLogic.getCharacterAndVisibilityBo(characterAndVisibilityAndPlayerBo);
        return characterBusinessLogicConverter.getChangeVisibilityOfCharacterDetailsResponseFromCharacterAndVisibilityBo(characterAndVisibilityBo);
    }
}