package services.classdetailservice.bll;

import com.google.inject.Inject;
import services.classdetailservice.bll.bo.ClassBo;
import services.classdetailservice.bll.bo.ClassDetailsBo;
import services.classdetailservice.dal.ClassDetailDataAccess;
import services.classdetailservice.dal.ClassDetailDataAccessConverter;
import services.classdetailservice.dal.dao.ClassDao;
import services.classdetailservice.dal.dao.ClassDetailsDao;

public class ClassDetailBusinessLogicImpl implements ClassDetailBusinessLogic {
    @Inject
    private ClassDetailDataAccessConverter classDetailDataAccessConverter;
    @Inject
    private ClassDetailDataAccess classDetailDataAccess;

    public ClassDetailsBo getClassDetailsBo(ClassBo classBo) {
        ClassDao classDao = classDetailDataAccessConverter.getClassDaoFromClassBo(classBo);
        ClassDetailsDao classDetailsDao = classDetailDataAccess.getClassDetailsDao(classDao);
        return classDetailDataAccessConverter.getClassDetailsBoFromClassDetailsDao(classDetailsDao);
    }
}