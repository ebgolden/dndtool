package services.resultservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.DataOperator;
import services.resultservice.dal.dao.ResultDao;
import services.resultservice.dal.dao.ResultAndVisibilityDao;

public class ResultDataAccessImpl implements ResultDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private ResultDataAccessConverter resultDataAccessConverter;

    public ResultAndVisibilityDao getResultAndVisibilityDao(ResultDao resultDao) {
        String resultJson = resultDao.getResultJson();
        dataOperator.sendRequestJson(api, resultJson);
        String resultAndVisibilityJson = dataOperator.getResponseJson();
        return resultDataAccessConverter.getResultAndVisibilityDaoFromResultAndVisibilityJson(resultAndVisibilityJson);
    }

    public ResultAndVisibilityDao getResultAndVisibilityDao(ResultAndVisibilityDao resultAndVisibilityDao) {
        String resultAndVisibilityJson = resultAndVisibilityDao.getResultAndVisibilityJson();
        dataOperator.sendRequestJson(api, resultAndVisibilityJson);
        resultAndVisibilityJson = dataOperator.getResponseJson();
        return resultDataAccessConverter.getResultAndVisibilityDaoFromResultAndVisibilityJson(resultAndVisibilityJson);
    }
}