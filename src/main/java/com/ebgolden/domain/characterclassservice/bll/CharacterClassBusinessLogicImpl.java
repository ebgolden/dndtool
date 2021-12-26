package domain.characterclassservice.bll;

import com.google.inject.Inject;
import domain.characterclassservice.bll.bo.CharacterClassBo;
import domain.characterclassservice.dal.CharacterClassDataAccess;
import domain.characterclassservice.dal.CharacterClassDataAccessConverter;
import domain.characterclassservice.dal.dao.CharacterClassDao;

public class CharacterClassBusinessLogicImpl implements CharacterClassBusinessLogic {
    @Inject
    private CharacterClassDataAccessConverter characterClassDataAccessConverter;
    @Inject
    private CharacterClassDataAccess characterClassDataAccess;

    public CharacterClassBo getCharacterClassBo(CharacterClassBo characterClassBo) {
        CharacterClassDao characterClassDao = characterClassDataAccessConverter.getCharacterClassDaoFromCharacterClassBo(characterClassBo);
        characterClassDao = characterClassDataAccess.getCharacterClassDao(characterClassDao);
        return characterClassDataAccessConverter.getCharacterClassBoFromCharacterClassDao(characterClassDao);
    }
}