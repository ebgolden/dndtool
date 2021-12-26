package domain.characterservice;

import com.google.inject.Inject;
import domain.characterservice.bll.CharacterBusinessLogic;
import domain.characterservice.bll.CharacterBusinessLogicConverter;
import domain.characterservice.bll.bo.NonPlayableCharacterAndVisibilityAndDungeonMasterBo;
import domain.characterservice.bll.bo.NonPlayableCharacterBo;

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