package com.ebgolden.domain.resultservice.dal;

import com.ebgolden.domain.resultservice.dal.dao.ResultDao;
import com.ebgolden.domain.resultservice.dal.dao.ResultAndVisibilityDao;

public interface ResultDataAccess {
    ResultAndVisibilityDao getResultAndVisibilityDao(ResultDao resultDao);

    ResultAndVisibilityDao getResultAndVisibilityDao(ResultAndVisibilityDao resultAndVisibilityDao);
}