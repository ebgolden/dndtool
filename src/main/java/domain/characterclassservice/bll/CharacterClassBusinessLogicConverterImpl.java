package domain.characterclassservice.bll;

import common.CharacterClass;
import domain.characterclassservice.UpdatedCharacterClassRequest;
import domain.characterclassservice.UpdatedCharacterClassResponse;
import domain.characterclassservice.bll.bo.CharacterClassBo;

public class CharacterClassBusinessLogicConverterImpl implements CharacterClassBusinessLogicConverter {
    public CharacterClassBo getCharacterClassBoFromUpdatedCharacterClassRequest(UpdatedCharacterClassRequest updatedCharacterClassRequest) {
        CharacterClass characterClass = updatedCharacterClassRequest.getCharacterClass();
        return CharacterClassBo
                .builder()
                .characterClass(characterClass)
                .build();
    }

    public UpdatedCharacterClassResponse getUpdatedCharacterClassResponseFromCharacterClassBo(CharacterClassBo characterClassBo) {
        CharacterClass characterClass = characterClassBo.getCharacterClass();
        return UpdatedCharacterClassResponse
                .builder()
                .characterClass(characterClass)
                .build();
    }
}