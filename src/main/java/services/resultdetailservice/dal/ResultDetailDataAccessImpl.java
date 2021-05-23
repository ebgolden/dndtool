package services.resultdetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.resultdetailservice.dal.dao.ResultDao;
import services.resultdetailservice.dal.dao.ResultDetailsAndVisibilityDao;

public class ResultDetailDataAccessImpl implements ResultDetailDataAccess {
    @Inject
    private ResultDetailDataAccessConverter resultDetailDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public ResultDetailsAndVisibilityDao getResultDetailsAndVisibilityDao(ResultDao resultDao) {
        String resultJson = resultDao.getResultJson();
        dataOperator.sendRequestJson(resultJson);
        String latestJsonObject = dataOperator.getResponseJson();
        return resultDetailDataAccessConverter.getResultDetailsAndVisibilityDaoFromLatestJsonObject(latestJsonObject);
    }

    public ResultDetailsAndVisibilityDao getResultDetailsAndVisibilityDao(ResultDetailsAndVisibilityDao resultDetailsAndVisibilityDao) {
        String resultAndVisibilityJson = resultDetailsAndVisibilityDao.getResultDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(resultAndVisibilityJson);
        String updatedJsonObject = dataOperator.getResponseJson();
        return resultDetailDataAccessConverter.getResultDetailsAndVisibilityDaoFromLatestJsonObject(updatedJsonObject);
    }
}