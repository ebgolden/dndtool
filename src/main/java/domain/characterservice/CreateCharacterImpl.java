package domain.characterservice;

import com.google.inject.Inject;
import domain.characterservice.bll.CharacterBusinessLogic;
import domain.characterservice.bll.CharacterBusinessLogicConverter;
import domain.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import domain.characterservice.bll.bo.CharacterBo;

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