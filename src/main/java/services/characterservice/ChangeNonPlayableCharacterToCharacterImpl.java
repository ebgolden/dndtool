package services.characterservice;

import com.google.inject.Inject;
import services.characterservice.bll.CharacterBusinessLogic;
import services.characterservice.bll.CharacterBusinessLogicConverter;
import services.characterservice.bll.bo.CharacterBo;
import services.characterservice.bll.bo.NonPlayableCharacterAndDungeonMasterBo;

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