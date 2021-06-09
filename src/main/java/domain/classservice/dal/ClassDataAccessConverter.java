package domain.classservice.dal;

import domain.classservice.bll.bo.ClassBo;
import domain.classservice.dal.dao.ClassDao;

public interface ClassDataAccessConverter {
    ClassDao getClassDaoFromClassBo(ClassBo classBo);

    ClassBo getClassBoFromClassDao(ClassDao classDao);

    ClassDao getClassDaoFromClassJson(String classJson);
}