package domain.classservice.dal;

import domain.classservice.dal.dao.ClassDao;

public interface ClassDataAccess {
    ClassDao getClassDao(ClassDao classDao);
}