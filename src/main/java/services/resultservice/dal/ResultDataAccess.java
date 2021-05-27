package services.resultservice.dal;

import services.resultservice.dal.dao.ResultDao;
import services.resultservice.dal.dao.ResultAndVisibilityDao;

public interface ResultDataAccess {
    ResultAndVisibilityDao getResultAndVisibilityDao(ResultDao resultDao);

    ResultAndVisibilityDao getResultAndVisibilityDao(ResultAndVisibilityDao resultAndVisibilityDao);
}