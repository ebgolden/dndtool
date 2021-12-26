package com.ebgolden.application.armorreaderservice.module;

import com.ebgolden.application.armorreaderservice.GetArmorFromResource;
import com.ebgolden.application.armorreaderservice.GetArmorFromResourceImpl;
import com.ebgolden.application.armorreaderservice.bll.ArmorReaderBusinessLogic;
import com.ebgolden.application.armorreaderservice.bll.ArmorReaderBusinessLogicConverter;
import com.ebgolden.application.armorreaderservice.bll.ArmorReaderBusinessLogicConverterImpl;
import com.ebgolden.application.armorreaderservice.bll.ArmorReaderBusinessLogicImpl;
import com.ebgolden.application.armorreaderservice.dal.ArmorReaderDataAccess;
import com.ebgolden.application.armorreaderservice.dal.ArmorReaderDataAccessConverter;
import com.ebgolden.application.armorreaderservice.dal.ArmorReaderDataAccessConverterImpl;
import com.ebgolden.application.armorreaderservice.dal.ArmorReaderDataAccessImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class ArmorReaderModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetArmorFromResource.class).to(GetArmorFromResourceImpl.class);
        bind(ArmorReaderBusinessLogicConverter.class).to(ArmorReaderBusinessLogicConverterImpl.class);
        bind(ArmorReaderBusinessLogic.class).to(ArmorReaderBusinessLogicImpl.class);
        bind(ArmorReaderDataAccessConverter.class).to(ArmorReaderDataAccessConverterImpl.class);
        bind(ArmorReaderDataAccess.class).to(ArmorReaderDataAccessImpl.class);
    }

    @Provides
    @Named("armorDirectory")
    public String provideArmorDirectory() {
        return "src/main/java/com/ebgolden/resources/items/armor/";
    }

    @Provides
    @Named("armorSuffix")
    public String provideCharacterClassSuffix() {
        return ".txt";
    }
}