package application.characterclassreaderservice.module;

import application.characterclassreaderservice.GetCharacterClassFromResource;
import application.characterclassreaderservice.GetCharacterClassFromResourceImpl;
import application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogic;
import application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogicConverter;
import application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogicConverterImpl;
import application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogicImpl;
import application.characterclassreaderservice.dal.CharacterClassReaderDataAccess;
import application.characterclassreaderservice.dal.CharacterClassReaderDataAccessConverter;
import application.characterclassreaderservice.dal.CharacterClassReaderDataAccessConverterImpl;
import application.characterclassreaderservice.dal.CharacterClassReaderDataAccessImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class CharacterClassReaderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetCharacterClassFromResource.class).to(GetCharacterClassFromResourceImpl.class);
        bind(CharacterClassReaderBusinessLogicConverter.class).to(CharacterClassReaderBusinessLogicConverterImpl.class);
        bind(CharacterClassReaderBusinessLogic.class).to(CharacterClassReaderBusinessLogicImpl.class);
        bind(CharacterClassReaderDataAccessConverter.class).to(CharacterClassReaderDataAccessConverterImpl.class);
        bind(CharacterClassReaderDataAccess.class).to(CharacterClassReaderDataAccessImpl.class);
    }

    @Provides
    @Named("characterClassDirectory")
    public String provideCharacterClassDirectory() {
        return "src/main/java/com/ebgolden/resources/characterclasses/";
    }

    @Provides
    @Named("characterClassSuffix")
    public String provideCharacterClassSuffix() {
        return ".txt";
    }
}