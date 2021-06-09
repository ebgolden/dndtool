package domain.classservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Class;
import domain.classservice.bll.bo.ClassBo;
import domain.classservice.dal.dao.ClassDao;

public class ClassDataAccessConverterImpl implements ClassDataAccessConverter {
    public ClassDao getClassDaoFromClassBo(ClassBo classBo) {
        Class cClass = classBo.getCClass();
        ObjectMapper objectMapper = new ObjectMapper();
        String classJson = "{}";
        try {
            classJson = objectMapper.writeValueAsString(cClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ClassDao
                .builder()
                .classJson(classJson)
                .build();
    }

    public ClassBo getClassBoFromClassDao(ClassDao classDao) {
        String classJson = classDao.getClassJson();
        if (classJson == null)
            classJson = "{}";
        Class cClass = null;
        if (!classJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            cClass = Class
                    .builder()
                    .build();
            try {
                cClass = objectMapper.readValue(classJson, Class.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ClassBo
                .builder()
                .cClass(cClass)
                .build();
    }

    public ClassDao getClassDaoFromClassJson(String classJson) {
        return ClassDao
                .builder()
                .classJson(classJson)
                .build();
    }
}