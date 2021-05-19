package services.classdetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.classdetailservice.dal.dao.ClassDao;
import services.classdetailservice.dal.dao.ClassDetailsDao;

public class ClassDetailDataAccessImpl implements ClassDetailDataAccess {
    @Inject
    ClassDetailDataAccessConverter classDetailDataAccessConverter;
    @Inject
    DataOperator dataOperator;

    public ClassDetailsDao getClassDetailsDao(ClassDao classDao) {
        String classJson = classDao.getClassJson();
        dataOperator.sendRequestJson(classJson);
        String latestJsonObject = dataOperator.getResponseJson();
        return classDetailDataAccessConverter.getClassDetailsDaoFromLatestJsonObject(latestJsonObject);
    }
}