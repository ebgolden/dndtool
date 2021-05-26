package services.classdetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.classdetailservice.dal.dao.ClassDao;
import services.classdetailservice.dal.dao.ClassDetailsDao;

public class ClassDetailDataAccessImpl implements ClassDetailDataAccess {
    @Inject
    private DataOperator dataOperator;
    @Inject
    private ClassDetailDataAccessConverter classDetailDataAccessConverter;

    public ClassDetailsDao getClassDetailsDao(ClassDao classDao) {
        String classJson = classDao.getClassJson();
        dataOperator.sendRequestJson(classJson);
        String classDetailsJson = dataOperator.getResponseJson();
        return classDetailDataAccessConverter.getClassDetailsDaoFromClassDetailsJson(classDetailsJson);
    }
}