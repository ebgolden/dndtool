package services.classdetailservice.dal;

import services.classdetailservice.dal.dao.ClassDao;
import services.classdetailservice.dal.dao.ClassDetailsDao;

public interface ClassDetailDataAccess {
    ClassDetailsDao getClassDetailsDao(ClassDao classDao);
}