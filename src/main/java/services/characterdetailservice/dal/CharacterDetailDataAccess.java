package services.characterdetailservice.dal;

import services.characterdetailservice.dal.dao.CharacterDao;
import services.characterdetailservice.dal.dao.CharacterDetailsAndVisibilityDao;

public interface CharacterDetailDataAccess {
    CharacterDetailsAndVisibilityDao getCharacterDetailsAndVisibilityDao(CharacterDao characterDao);
}