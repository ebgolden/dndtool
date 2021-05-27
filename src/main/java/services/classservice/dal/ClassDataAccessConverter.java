package services.classservice.dal;

import services.classservice.bll.bo.ClassBo;
import services.classservice.dal.dao.ClassDao;

public interface ClassDataAccessConverter {
    ClassDao getClassDaoFromClassBo(ClassBo classBo);

    ClassBo getClassBoFromClassDao(ClassDao classDao);

    ClassDao getClassDaoFromClassJson(String classJson);
}