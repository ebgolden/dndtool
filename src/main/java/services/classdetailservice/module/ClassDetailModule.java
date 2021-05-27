package services.classdetailservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.classdetailservice.GetClassDetails;
import services.classdetailservice.GetClassDetailsImpl;
import services.classdetailservice.bll.ClassDetailBusinessLogic;
import services.classdetailservice.bll.ClassDetailBusinessLogicConverter;
import services.classdetailservice.bll.ClassDetailBusinessLogicConverterImpl;
import services.classdetailservice.bll.ClassDetailBusinessLogicImpl;
import services.classdetailservice.dal.ClassDetailDataAccess;
import services.classdetailservice.dal.ClassDetailDataAccessConverter;
import services.classdetailservice.dal.ClassDetailDataAccessConverterImpl;
import services.classdetailservice.dal.ClassDetailDataAccessImpl;

public class ClassDetailModule extends AbstractModule {
    private final Object API;

    public ClassDetailModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetClassDetails.class).to(GetClassDetailsImpl.class);
        bind(ClassDetailBusinessLogicConverter.class).to(ClassDetailBusinessLogicConverterImpl.class);
        bind(ClassDetailBusinessLogic.class).to(ClassDetailBusinessLogicImpl.class);
        bind(ClassDetailDataAccessConverter.class).to(ClassDetailDataAccessConverterImpl.class);
        bind(ClassDetailDataAccess.class).to(ClassDetailDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}