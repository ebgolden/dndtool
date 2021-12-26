package com.ebgolden.domain.characterservice;

import com.google.inject.Inject;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogic;
import com.ebgolden.domain.characterservice.bll.CharacterBusinessLogicConverter;
import com.ebgolden.domain.characterservice.bll.bo.CharacterBo;
import com.ebgolden.domain.characterservice.bll.bo.NonPlayableCharacterAndDungeonMasterBo;

public class ChangeNonPlayableCharacterToCharacterImpl implements ChangeNonPlayableCharacterToCharacter {
    @Inject
    private CharacterBusinessLogicConverter characterBusinessLogicConverter;
    @Inject
    private CharacterBusinessLogic characterBusinessLogic;

    public ChangeNonPlayableCharacterToCharacterResponse getChangeNonPlayableCharacterToCharacterResponse(ChangeNonPlayableCharacterToCharacterRequest changeNonPlayableCharacterToCharacterRequest) {
        NonPlayableCharacterAndDungeonMasterBo nonPlayableCharacterAndDungeonMasterBo = characterBusinessLogicConverter.getNonPlayableCharacterAndDungeonMasterBoFromChangeNonPlayableCharacterToCharacterRequest(changeNonPlayableCharacterToCharacterRequest);
        CharacterBo characterBo = characterBusinessLogic.getCharacterBo(nonPlayableCharacterAndDungeonMasterBo);
        return characterBusinessLogicConverter.getChangeNonPlayableCharacterToCharacterResponseFromCharacterBo(characterBo);
    }
}