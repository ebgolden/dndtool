package application.characterclassreaderservice.bll;

import application.characterclassreaderservice.CharacterClassFromResourceRequest;
import application.characterclassreaderservice.CharacterClassFromResourceResponse;
import application.characterclassreaderservice.bll.bo.CharacterClassBo;
import application.characterclassreaderservice.bll.bo.CharacterClassNameBo;
import common.CharacterClass;

public class CharacterClassReaderBusinessLogicConverterImpl implements CharacterClassReaderBusinessLogicConverter {
    public CharacterClassNameBo getCharacterClassNameBoFromCharacterClassFromResourceRequest(CharacterClassFromResourceRequest characterClassFromResourceRequest) {
        String characterClassName = characterClassFromResourceRequest.getCharacterClassName();
        return CharacterClassNameBo
                .builder()
                .characterClassName(characterClassName)
                .build();
    }

    public CharacterClassFromResourceResponse getCharacterClassFromResourceResponseFromCharacterClassBo(CharacterClassBo characterClassBo) {
        CharacterClass characterClass = characterClassBo.getCharacterClass();
        return CharacterClassFromResourceResponse
                .builder()
                .characterClass(characterClass)
                .build();
    }
}