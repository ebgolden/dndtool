package com.ebgolden.domain.characterservice;

import com.google.inject.Inject;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogic;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogicConverter;
import com.ebgolden.domain.characterservice.bll.bo.CharacterAndDungeonMasterBo;
import com.ebgolden.domain.characterservice.bll.bo.NonPlayableCharacterBo;

public class ChangeCharacterToNonPlayableCharacterImpl implements ChangeCharacterToNonPlayableCharacter {
    @Inject
    private CharacterBusinessLogicConverter characterBusinessLogicConverter;
    @Inject
    private CharacterBusinessLogic characterBusinessLogic;

    public ChangeCharacterToNonPlayableCharacterResponse getChangeCharacterToNonPlayableCharacterResponse(ChangeCharacterToNonPlayableCharacterRequest changeCharacterToNonPlayableCharacterRequest) {
        CharacterAndDungeonMasterBo characterAndDungeonMasterBo = characterBusinessLogicConverter.getCharacterAndDungeonMasterBoFromChangeCharacterToNonPlayableCharacterRequest(changeCharacterToNonPlayableCharacterRequest);
        NonPlayableCharacterBo nonPlayableCharacterBo = characterBusinessLogic.getNonPlayableCharacterBo(characterAndDungeonMasterBo);
        return characterBusinessLogicConverter.getChangeCharacterToNonPlayableCharacterResponseFromNonPlayableCharacterBo(nonPlayableCharacterBo);
    }
}