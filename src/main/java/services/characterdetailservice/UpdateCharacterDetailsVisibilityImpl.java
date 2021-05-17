package services.characterdetailservice;

import com.google.inject.Inject;
import services.characterdetailservice.bll.CharacterDetailBusinessLogic;
import services.characterdetailservice.bll.CharacterDetailBusinessLogicConverter;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityBo;

public class UpdateCharacterDetailsVisibilityImpl implements UpdateCharacterDetailsVisibility {
    @Inject
    private CharacterDetailBusinessLogicConverter characterDetailBusinessLogicConverter;
    @Inject
    private CharacterDetailBusinessLogic characterDetailBusinessLogic;

    public CharacterDetailsVisibilityResponse getCharacterDetailsVisibilityResponse(CharacterDetailsVisibilityRequest characterDetailsVisibilityRequest) {
        CharacterDetailsAndVisibilityAndPlayerBo characterDetailsAndVisibilityAndPlayerBo = characterDetailBusinessLogicConverter.getCharacterDetailsAndVisibilityAndPlayerBoFromCharacterDetailsVisibilityRequest(characterDetailsVisibilityRequest);
        CharacterDetailsAndVisibilityBo characterDetailsAndVisibilityBo = characterDetailBusinessLogic.getCharacterDetailsAndVisibilityBo(characterDetailsAndVisibilityAndPlayerBo);
        return characterDetailBusinessLogicConverter.getCharacterDetailsVisibilityResponseFromCharacterDetailsAndVisibilityBo(characterDetailsAndVisibilityBo);
    }
}