package services.characterservice.dal;

import services.characterservice.bll.bo.CharacterAndVisibilityAndPlayerBo;
import services.characterservice.bll.bo.CharacterBo;
import services.characterservice.dal.dao.CharacterAndVisibilityAndPlayerDao;
import services.characterservice.dal.dao.CharacterDao;

public interface CharacterDataAccessConverter {
    CharacterAndVisibilityAndPlayerDao getCharacterAndVisibilityAndPlayerDaoFromCharacterAndVisibilityAndPlayerBo(CharacterAndVisibilityAndPlayerBo characterAndVisibilityAndPlayerBo);

    CharacterBo getCharacterBoFromCharacterDao(CharacterDao characterDao);

    CharacterDao getCharacterDaoFromCharacterJsonObject(String characterJsonObject);
}