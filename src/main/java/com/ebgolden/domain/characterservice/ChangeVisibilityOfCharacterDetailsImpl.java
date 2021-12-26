package domain.characterservice;

import com.google.inject.Inject;
import domain.characterservice.bll.CharacterBusinessLogic;
import domain.characterservice.bll.CharacterBusinessLogicConverter;
import domain.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import domain.characterservice.bll.bo.CharacterAndVisibilityBo;

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