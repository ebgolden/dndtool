package services.characterservice;

import com.google.inject.Inject;
import services.characterservice.bll.CharacterBusinessLogic;
import services.characterservice.bll.CharacterBusinessLogicConverter;
import services.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import services.characterservice.bll.bo.CharacterAndVisibilityBo;

public class ChangeVisibilityOfCharacterDetailsImpl implements ChangeVisibilityOfCharacterDetails {
    @Inject
    private CharacterBusinessLogicConverter characterBusinessLogicConverter;
    @Inject
    private CharacterBusinessLogic characterBusinessLogic;

    public ChangeVisibilityOfCharacterDetailsResponse getChangeVisibilityOfCharacterDetailsResponse(ChangeVisibilityOfCharacterDetailsRequest changeVisibilityOfUpdatedCharacterRequest) {
        CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo = characterBusinessLogicConverter.getCharacterAndVisibilityAndPlayerBoFromChangeVisibilityOfCharacterDetailsRequest(changeVisibilityOfUpdatedCharacterRequest);
        CharacterAndVisibilityBo characterAndVisibilityBo = characterBusinessLogic.getCharacterAndVisibilityBo(characterAndVisibilityAndPlayerBo);
        return characterBusinessLogicConverter.getChangeVisibilityOfCharacterDetailsResponseFromCharacterAndVisibilityBo(characterAndVisibilityBo);
    }
}