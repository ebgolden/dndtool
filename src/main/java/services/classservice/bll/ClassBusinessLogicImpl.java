package services.classservice.bll;

import com.google.inject.Inject;
import services.classservice.bll.bo.ClassBo;
import services.classservice.dal.ClassDataAccess;
import services.classservice.dal.ClassDataAccessConverter;
import services.classservice.dal.dao.ClassDao;

public class ClassBusinessLogicImpl implements ClassBusinessLogic {
    @Inject
    private ClassDataAccessConverter classDataAccessConverter;
    @Inject
    private ClassDataAccess classDataAccess;

    public ClassBo getClassBo(ClassBo classBo) {
        ClassDao classDao = classDataAccessConverter.getClassDaoFromClassBo(classBo);
        classDao = classDataAccess.getClassDao(classDao);
        return classDataAccessConverter.getClassBoFromClassDao(classDao);
    }
}