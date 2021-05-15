package services.characterdetailservice.bll;

import services.characterdetailservice.bll.bo.CharacterAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityBo;

public interface CharacterDetailBusinessLogic {
    CharacterDetailsAndVisibilityBo getCharacterDetailsBo(CharacterAndPlayerBo characterAndPlayerBo);
}