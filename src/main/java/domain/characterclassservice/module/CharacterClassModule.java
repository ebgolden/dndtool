package domain.characterclassservice.module;

import com.google.inject.AbstractModule;
import domain.characterclassservice.GetUpdatedCharacterClass;
import domain.characterclassservice.GetUpdatedCharacterClassImpl;
import domain.characterclassservice.bll.CharacterClassBusinessLogic;
import domain.characterclassservice.bll.CharacterClassBusinessLogicConverter;
import domain.characterclassservice.bll.CharacterClassBusinessLogicConverterImpl;
import domain.characterclassservice.bll.CharacterClassBusinessLogicImpl;
import domain.characterclassservice.dal.CharacterClassDataAccess;
import domain.characterclassservice.dal.CharacterClassDataAccessConverter;
import domain.characterclassservice.dal.CharacterClassDataAccessConverterImpl;
import domain.characterclassservice.dal.CharacterClassDataAccessImpl;

public class CharacterClassModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetUpdatedCharacterClass.class).to(GetUpdatedCharacterClassImpl.class);
        bind(CharacterClassBusinessLogicConverter.class).to(CharacterClassBusinessLogicConverterImpl.class);
        bind(CharacterClassBusinessLogic.class).to(CharacterClassBusinessLogicImpl.class);
        bind(CharacterClassDataAccessConverter.class).to(CharacterClassDataAccessConverterImpl.class);
        bind(CharacterClassDataAccess.class).to(CharacterClassDataAccessImpl.class);
    }
}