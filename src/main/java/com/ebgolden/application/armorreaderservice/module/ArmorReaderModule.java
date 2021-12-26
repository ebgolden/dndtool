package application.armorreaderservice.module;

import application.armorreaderservice.GetArmorFromResource;
import application.armorreaderservice.GetArmorFromResourceImpl;
import application.armorreaderservice.bll.ArmorReaderBusinessLogic;
import application.armorreaderservice.bll.ArmorReaderBusinessLogicConverter;
import application.armorreaderservice.bll.ArmorReaderBusinessLogicConverterImpl;
import application.armorreaderservice.bll.ArmorReaderBusinessLogicImpl;
import application.armorreaderservice.dal.ArmorReaderDataAccess;
import application.armorreaderservice.dal.ArmorReaderDataAccessConverter;
import application.armorreaderservice.dal.ArmorReaderDataAccessConverterImpl;
import application.armorreaderservice.dal.ArmorReaderDataAccessImpl;
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