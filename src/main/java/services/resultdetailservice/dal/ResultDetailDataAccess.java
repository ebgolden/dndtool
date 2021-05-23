package services.resultdetailservice.dal;

import services.resultdetailservice.dal.dao.ResultDao;
import services.resultdetailservice.dal.dao.ResultDetailsAndVisibilityDao;

public interface ResultDetailDataAccess {
    ResultDetailsAndVisibilityDao getResultDetailsAndVisibilityDao(ResultDao resultDao);

    ResultDetailsAndVisibilityDao getResultDetailsAndVisibilityDao(ResultDetailsAndVisibilityDao resultDetailsAndVisibilityDao);
}