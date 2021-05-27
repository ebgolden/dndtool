package services.resultdetailservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import objects.DataOperator;
import services.resultdetailservice.dal.dao.ResultDao;
import services.resultdetailservice.dal.dao.ResultDetailsAndVisibilityDao;

public class ResultDetailDataAccessImpl implements ResultDetailDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private ResultDetailDataAccessConverter resultDetailDataAccessConverter;

    public ResultDetailsAndVisibilityDao getResultDetailsAndVisibilityDao(ResultDao resultDao) {
        String resultJson = resultDao.getResultJson();
        dataOperator.sendRequestJson(api, resultJson);
        String resultDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return resultDetailDataAccessConverter.getResultDetailsAndVisibilityDaoFromResultDetailsAndVisibilityJson(resultDetailsAndVisibilityJson);
    }

    public ResultDetailsAndVisibilityDao getResultDetailsAndVisibilityDao(ResultDetailsAndVisibilityDao resultDetailsAndVisibilityDao) {
        String resultAndVisibilityJson = resultDetailsAndVisibilityDao.getResultDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(api, resultAndVisibilityJson);
        String resultDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return resultDetailDataAccessConverter.getResultDetailsAndVisibilityDaoFromResultDetailsAndVisibilityJson(resultDetailsAndVisibilityJson);
    }
}