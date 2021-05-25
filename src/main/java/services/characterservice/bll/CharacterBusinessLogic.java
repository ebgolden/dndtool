package services.characterservice.bll;

import services.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import services.characterservice.bll.bo.CharacterBo;

public interface CharacterBusinessLogic {
    CharacterBo getCharacterBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo);
}