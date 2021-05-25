package services.characterservice;

import com.google.inject.Inject;
import services.characterservice.bll.CharacterBusinessLogic;
import services.characterservice.bll.CharacterBusinessLogicConverter;
import services.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import services.characterservice.bll.bo.CharacterBo;

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