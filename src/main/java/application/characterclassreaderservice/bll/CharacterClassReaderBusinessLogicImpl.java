package application.characterclassreaderservice.bll;

import application.characterclassreaderservice.bll.bo.CharacterClassBo;
import application.characterclassreaderservice.bll.bo.CharacterClassNameBo;
import application.characterclassreaderservice.dal.CharacterClassReaderDataAccess;
import application.characterclassreaderservice.dal.CharacterClassReaderDataAccessConverter;
import application.characterclassreaderservice.dal.dao.CharacterClassDao;
import application.characterclassreaderservice.dal.dao.CharacterClassNameDao;
import com.google.inject.Inject;

public class CharacterClassReaderBusinessLogicImpl implements CharacterClassReaderBusinessLogic {
    @Inject
    private CharacterClassReaderDataAccessConverter characterClassReaderDataAccessConverter;
    @Inject
    private CharacterClassReaderDataAccess characterClassReaderDataAccess;

    public CharacterClassBo getCharacterClassBo(CharacterClassNameBo characterClassNameBo) {
        CharacterClassNameDao characterClassNameDao = characterClassReaderDataAccessConverter.getCharacterClassNameDaoFromCharacterClassNameBo(characterClassNameBo);
        CharacterClassDao characterClassDao = characterClassReaderDataAccess.getCharacterClassDao(characterClassNameDao);
        if (characterClassDao.getCharacterClassJson().equals("{}"))
            return CharacterClassBo
                    .builder()
                    .build();
        return characterClassReaderDataAccessConverter.getCharacterClassBoFromCharacterClassDao(characterClassDao);
    }
}