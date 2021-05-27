package services.classservice.dal;

import services.classservice.dal.dao.ClassDao;

public interface ClassDataAccess {
    ClassDao getClassDao(ClassDao classDao);
}