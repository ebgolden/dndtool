package com.ebgolden.domain.characterclassservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.characterclassservice.GetUpdatedCharacterClass;
import com.ebgolden.domain.characterclassservice.GetUpdatedCharacterClassImpl;
import com.ebgolden.domain.characterclassservice.bll.CharacterClassBusinessLogic;
import com.ebgolden.domain.characterclassservice.bll.CharacterClassBusinessLogicConverter;
import com.ebgolden.domain.characterclassservice.bll.CharacterClassBusinessLogicConverterImpl;
import com.ebgolden.domain.characterclassservice.bll.CharacterClassBusinessLogicImpl;
import com.ebgolden.domain.characterclassservice.dal.CharacterClassDataAccess;
import com.ebgolden.domain.characterclassservice.dal.CharacterClassDataAccessConverter;
import com.ebgolden.domain.characterclassservice.dal.CharacterClassDataAccessConverterImpl;
import com.ebgolden.domain.characterclassservice.dal.CharacterClassDataAccessImpl;

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