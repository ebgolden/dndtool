package com.ebgolden.application.characterclassreaderservice.module;

import com.ebgolden.application.characterclassreaderservice.GetCharacterClassFromResource;
import com.ebgolden.application.characterclassreaderservice.GetCharacterClassFromResourceImpl;
import com.ebgolden.application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogic;
import com.ebgolden.application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogicConverter;
import com.ebgolden.application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogicConverterImpl;
import com.ebgolden.application.characterclassreaderservice.bll.CharacterClassReaderBusinessLogicImpl;
import com.ebgolden.application.characterclassreaderservice.dal.CharacterClassReaderDataAccess;
import com.ebgolden.application.characterclassreaderservice.dal.CharacterClassReaderDataAccessConverter;
import com.ebgolden.application.characterclassreaderservice.dal.CharacterClassReaderDataAccessConverterImpl;
import com.ebgolden.application.characterclassreaderservice.dal.CharacterClassReaderDataAccessImpl;
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