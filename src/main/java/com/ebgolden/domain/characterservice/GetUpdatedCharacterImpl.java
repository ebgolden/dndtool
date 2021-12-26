package domain.characterservice;

import com.google.inject.Inject;
import domain.characterservice.bll.CharacterBusinessLogic;
import domain.characterservice.bll.CharacterBusinessLogicConverter;
import domain.characterservice.bll.bo.CharacterAndPlayerBo;
import domain.characterservice.bll.bo.CharacterAndVisibilityBo;

public class GetUpdatedCharacterImpl implements GetUpdatedCharacter {
    @Inject
    private CharacterBusinessLogicConverter characterBusinessLogicConverter;
    @Inject
    private CharacterBusinessLogic characterBusinessLogic;

    public UpdatedCharacterResponse getUpdatedCharacterResponse(UpdatedCharacterRequest updatedCharacterRequest) {
        CharacterAndPlayerBo characterAndPlayerBo = characterBusinessLogicConverter.getCharacterAndPlayerBoFromUpdatedCharacterRequest(updatedCharacterRequest);
        CharacterAndVisibilityBo characterAndVisibilityBo = characterBusinessLogic.getCharacterAndVisibilityBo(characterAndPlayerBo);
        return characterBusinessLogicConverter.getUpdatedCharacterResponseFromCharacterBo(characterAndVisibilityBo);
    }
}