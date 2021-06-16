package application.characterclassreaderservice;

import application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogic;
import application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogicConverter;
import application.characterclassreaderservice.bll.bo.CharacterClassBo;
import application.characterclassreaderservice.bll.bo.CharacterClassNameBo;
import com.google.inject.Inject;

public class GetCharacterClassFromResourceImpl implements GetCharacterClassFromResource {
    @Inject
    private CharacterClassReaderBusinessLogicConverter characterClassReaderBusinessLogicConverter;
    @Inject
    private CharacterClassReaderBusinessLogic characterClassReaderBusinessLogic;

    public CharacterClassFromResourceResponse getCharacterClassFromResourceResponse(CharacterClassFromResourceRequest characterClassFromResourceRequest) {
        CharacterClassNameBo characterClassNameBo = characterClassReaderBusinessLogicConverter.getCharacterClassNameBoFromCharacterClassFromResourceRequest(characterClassFromResourceRequest);
        CharacterClassBo characterClassBo = characterClassReaderBusinessLogic.getCharacterClassBo(characterClassNameBo);
        return characterClassReaderBusinessLogicConverter.getCharacterClassFromResourceResponseFromCharacterClassBo(characterClassBo);
    }
}