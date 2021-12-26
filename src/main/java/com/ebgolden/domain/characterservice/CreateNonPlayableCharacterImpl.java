package com.ebgolden.domain.characterservice;

import com.google.inject.Inject;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogic;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogicConverter;
import com.ebgolden.domain.characterservice.bll.bo.NonPlayableCharacterAndVisibilityAndDungeonMasterBo;
import com.ebgolden.domain.characterservice.bll.bo.NonPlayableCharacterBo;

public class CreateNonPlayableCharacterImpl implements CreateNonPlayableCharacter {
    @Inject
    private CharacterBusinessLogicConverter characterBusinessLogicConverter;
    @Inject
    private CharacterBusinessLogic characterBusinessLogic;

    public CreateNonPlayableCharacterResponse getCreateNonPlayableCharacterResponse(CreateNonPlayableCharacterRequest createNonPlayableCharacterRequest) {
        NonPlayableCharacterAndVisibilityAndDungeonMasterBo nonPlayableCharacterAndVisibilityAndDungeonMasterBo = characterBusinessLogicConverter.getNonPlayableCharacterAndVisibilityAndDungeonMasterBoFromCreateNonPlayableCharacterRequest(createNonPlayableCharacterRequest);
        NonPlayableCharacterBo nonPlayableCharacterBo = characterBusinessLogic.getNonPlayableCharacterBo(nonPlayableCharacterAndVisibilityAndDungeonMasterBo);
        return characterBusinessLogicConverter.getCreateNonPlayableCharacterResponseFromNonPlayableCharacterBo(nonPlayableCharacterBo);
    }
}