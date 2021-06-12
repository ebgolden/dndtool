package domain.characterclassservice.dal;

import domain.characterclassservice.bll.bo.CharacterClassBo;
import domain.characterclassservice.dal.dao.CharacterClassDao;

public interface CharacterClassDataAccessConverter {
    CharacterClassDao getCharacterClassDaoFromCharacterClassBo(CharacterClassBo characterClassBo);

    CharacterClassBo getCharacterClassBoFromCharacterClassDao(CharacterClassDao characterClassDao);

    CharacterClassDao getCharacterClassDaoFromCharacterClassJson(String characterClassJson);
}