package application.characterclassreaderservice.bll;

import application.characterclassreaderservice.bll.bo.CharacterClassBo;
import application.characterclassreaderservice.bll.bo.CharacterClassNameBo;

public interface CharacterClassReaderBusinessLogic {
    CharacterClassBo getCharacterClassBo(CharacterClassNameBo characterClassNameBo);
}