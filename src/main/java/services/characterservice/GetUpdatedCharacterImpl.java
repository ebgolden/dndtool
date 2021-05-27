package services.characterservice;

import com.google.inject.Inject;
import services.characterservice.bll.CharacterBusinessLogic;
import services.characterservice.bll.CharacterBusinessLogicConverter;
import services.characterservice.bll.bo.CharacterAndPlayerBo;
import services.characterservice.bll.bo.CharacterAndVisibilityBo;

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