package services.classdetailservice.dal;

import services.classdetailservice.bll.bo.ClassBo;
import services.classdetailservice.bll.bo.ClassDetailsBo;
import services.classdetailservice.dal.dao.ClassDao;
import services.classdetailservice.dal.dao.ClassDetailsDao;

public interface ClassDetailDataAccessConverter {
    ClassDao getClassDaoFromClassBo(ClassBo classBo);

    ClassDetailsBo getClassDetailsBoFromClassDetailsDao(ClassDetailsDao classDetailsDao);

    ClassDetailsDao getClassDetailsDaoFromClassDetailsJson(String classDetailsJson);
}