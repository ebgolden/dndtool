package services.characterservice;

import com.google.inject.Inject;
import services.characterservice.bll.CharacterBusinessLogic;
import services.characterservice.bll.CharacterBusinessLogicConverter;
import services.characterservice.bll.bo.NonPlayableCharacterAndVisibilityAndDungeonMasterBo;
import services.characterservice.bll.bo.NonPlayableCharacterBo;

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