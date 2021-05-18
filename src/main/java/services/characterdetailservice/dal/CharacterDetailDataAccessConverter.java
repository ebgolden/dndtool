package services.characterdetailservice.dal;

import services.characterdetailservice.bll.bo.CharacterAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityBo;
import services.characterdetailservice.dal.dao.CharacterDao;
import services.characterdetailservice.dal.dao.CharacterDetailsAndVisibilityDao;

public interface CharacterDetailDataAccessConverter {
    CharacterDao getCharacterDaoFromCharacterAndPlayerBo(CharacterAndPlayerBo characterAndPlayerBo);

    CharacterDetailsAndVisibilityDao getCharacterDetailsAndVisibilityDaoFromCharacterDetailsAndVisibilityBo(CharacterDetailsAndVisibilityBo characterDetailsAndVisibilityBo);

    CharacterDetailsAndVisibilityBo getCharacterDetailsAndVisibilityBoFromCharacterDetailsAndVisibilityDao(CharacterDetailsAndVisibilityDao characterDetailsAndVisibilityDao);

    CharacterDetailsAndVisibilityDao getCharacterDetailsAndVisibilityDaoFromLatestJsonObject(String latestJsonObject);
}