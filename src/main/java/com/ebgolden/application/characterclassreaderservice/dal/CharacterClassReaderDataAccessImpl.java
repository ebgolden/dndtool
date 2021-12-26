package application.characterclassreaderservice.dal;

import application.characterclassreaderservice.dal.dao.CharacterClassDao;
import application.characterclassreaderservice.dal.dao.CharacterClassNameDao;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public class CharacterClassReaderDataAccessImpl implements CharacterClassReaderDataAccess {
    @Inject
    @Named("characterClassDirectory")
    private String characterClassDirectory;
    @Inject
    @Named("characterClassSuffix")
    private String characterClassSuffix;
    @Inject
    private CharacterClassReaderDataAccessConverter characterClassReaderDataAccessConverter;

    public CharacterClassDao getCharacterClassDao(CharacterClassNameDao characterClassNameDao) {
        String characterClassName = characterClassNameDao.getCharacterClassName();
        String characterClassJson;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(characterClassDirectory + characterClassName + characterClassSuffix));
            characterClassJson = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            characterClassJson = "{}";
        }
        return characterClassReaderDataAccessConverter.getCharacterClassDaoFromCharacterClassJson(characterClassJson);
    }
}