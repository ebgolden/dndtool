package services.classservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.classservice.GetUpdatedClass;
import services.classservice.GetUpdatedClassImpl;
import services.classservice.bll.ClassBusinessLogic;
import services.classservice.bll.ClassBusinessLogicConverter;
import services.classservice.bll.ClassBusinessLogicConverterImpl;
import services.classservice.bll.ClassBusinessLogicImpl;
import services.classservice.dal.ClassDataAccess;
import services.classservice.dal.ClassDataAccessConverter;
import services.classservice.dal.ClassDataAccessConverterImpl;
import services.classservice.dal.ClassDataAccessImpl;

public class ClassModule extends AbstractModule {
    private final Object API;

    public ClassModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetUpdatedClass.class).to(GetUpdatedClassImpl.class);
        bind(ClassBusinessLogicConverter.class).to(ClassBusinessLogicConverterImpl.class);
        bind(ClassBusinessLogic.class).to(ClassBusinessLogicImpl.class);
        bind(ClassDataAccessConverter.class).to(ClassDataAccessConverterImpl.class);
        bind(ClassDataAccess.class).to(ClassDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}