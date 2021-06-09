package domain.classservice.module;

import com.google.inject.AbstractModule;
import domain.classservice.GetUpdatedClass;
import domain.classservice.GetUpdatedClassImpl;
import domain.classservice.bll.ClassBusinessLogic;
import domain.classservice.bll.ClassBusinessLogicConverter;
import domain.classservice.bll.ClassBusinessLogicConverterImpl;
import domain.classservice.bll.ClassBusinessLogicImpl;
import domain.classservice.dal.ClassDataAccess;
import domain.classservice.dal.ClassDataAccessConverter;
import domain.classservice.dal.ClassDataAccessConverterImpl;
import domain.classservice.dal.ClassDataAccessImpl;

public class ClassModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetUpdatedClass.class).to(GetUpdatedClassImpl.class);
        bind(ClassBusinessLogicConverter.class).to(ClassBusinessLogicConverterImpl.class);
        bind(ClassBusinessLogic.class).to(ClassBusinessLogicImpl.class);
        bind(ClassDataAccessConverter.class).to(ClassDataAccessConverterImpl.class);
        bind(ClassDataAccess.class).to(ClassDataAccessImpl.class);
    }
}