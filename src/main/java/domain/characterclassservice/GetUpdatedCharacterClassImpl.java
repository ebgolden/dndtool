package domain.characterclassservice;

import com.google.inject.Inject;
import domain.characterclassservice.bll.CharacterClassBusinessLogic;
import domain.characterclassservice.bll.CharacterClassBusinessLogicConverter;
import domain.characterclassservice.bll.bo.CharacterClassBo;

public class GetUpdatedCharacterClassImpl implements GetUpdatedCharacterClass {
    @Inject
    private CharacterClassBusinessLogicConverter characterClassBusinessLogicConverter;
    @Inject
    private CharacterClassBusinessLogic characterClassBusinessLogic;

    public UpdatedCharacterClassResponse getUpdatedCharacterClassResponse(UpdatedCharacterClassRequest updatedCharacterClassRequest) {
        CharacterClassBo characterClassBo = characterClassBusinessLogicConverter.getCharacterClassBoFromUpdatedCharacterClassRequest(updatedCharacterClassRequest);
        characterClassBo = characterClassBusinessLogic.getCharacterClassBo(characterClassBo);
        return characterClassBusinessLogicConverter.getUpdatedCharacterClassResponseFromCharacterClassBo(characterClassBo);
    }
}