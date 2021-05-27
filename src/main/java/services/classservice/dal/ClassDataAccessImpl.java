package services.classservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.DataOperator;
import services.classservice.dal.dao.ClassDao;

public class ClassDataAccessImpl implements ClassDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private ClassDataAccessConverter classDataAccessConverter;

    public ClassDao getClassDao(ClassDao classDao) {
        String classJson = classDao.getClassJson();
        dataOperator.sendRequestJson(api, classJson);
        classJson = dataOperator.getResponseJson();
        return classDataAccessConverter.getClassDaoFromClassJson(classJson);
    }
}