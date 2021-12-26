package com.ebgolden.domain.resultservice.dal;

import com.ebgolden.domain.resultservice.bll.bo.ResultAndPlayerBo;
import com.ebgolden.domain.resultservice.bll.bo.ResultAndVisibilityBo;
import com.ebgolden.domain.resultservice.dal.dao.ResultDao;
import com.ebgolden.domain.resultservice.dal.dao.ResultAndVisibilityDao;

public interface ResultDataAccessConverter {
    ResultDao getResultDaoFromResultAndPlayerBo(ResultAndPlayerBo resultAndPlayerBo);

    ResultAndVisibilityDao getResultAndVisibilityDaoFromResultAndVisibilityBo(ResultAndVisibilityBo resultAndVisibilityBo);

    ResultAndVisibilityBo getResultAndVisibilityBoFromResultAndVisibilityDao(ResultAndVisibilityDao resultAndVisibilityDao);

    ResultAndVisibilityDao getResultAndVisibilityDaoFromResultAndVisibilityJson(String resultAndVisibilityJson);
}