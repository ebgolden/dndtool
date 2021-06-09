package domain.classservice.bll;

import com.google.inject.Inject;
import domain.classservice.bll.bo.ClassBo;
import domain.classservice.dal.ClassDataAccess;
import domain.classservice.dal.ClassDataAccessConverter;
import domain.classservice.dal.dao.ClassDao;

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