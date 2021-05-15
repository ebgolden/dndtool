package services.characterdetailservice;

import com.google.inject.Inject;
import services.characterdetailservice.bll.CharacterDetailBusinessLogic;
import services.characterdetailservice.bll.CharacterDetailBusinessLogicConverter;
import services.characterdetailservice.bll.bo.CharacterAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityBo;

public class GetCharacterDetailsImpl implements GetCharacterDetails {
    @Inject
    private CharacterDetailBusinessLogicConverter characterDetailBusinessLogicConverter;
    @Inject
    private CharacterDetailBusinessLogic characterDetailBusinessLogic;

    public CharacterDetailsResponse getCharacterDetailsResponse(CharacterDetailsRequest characterDetailsRequest) {
        CharacterAndPlayerBo characterAndPlayerBo = characterDetailBusinessLogicConverter.getCharacterAndPlayerBoFromCharacterDetailsRequest(characterDetailsRequest);
        CharacterDetailsAndVisibilityBo characterDetailsAndVisibilityBo = characterDetailBusinessLogic.getCharacterDetailsBo(characterAndPlayerBo);
        return characterDetailBusinessLogicConverter.getCharacterDetailsResponseFromCharacterDetailsBo(characterDetailsAndVisibilityBo);
    }
}