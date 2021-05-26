package services.characterservice;

import com.google.inject.Inject;
import services.characterservice.bll.CharacterBusinessLogic;
import services.characterservice.bll.CharacterBusinessLogicConverter;
import services.characterservice.bll.bo.CharacterAndDungeonMasterBo;
import services.characterservice.bll.bo.NonPlayableCharacterBo;

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