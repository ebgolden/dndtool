package services.classdetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Class;
import services.classdetailservice.bll.bo.ClassBo;
import services.classdetailservice.bll.bo.ClassDetailsBo;
import services.classdetailservice.dal.dao.ClassDao;
import services.classdetailservice.dal.dao.ClassDetailsDao;

public class ClassDetailDataAccessConverterImpl implements ClassDetailDataAccessConverter {
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

    public ClassDetailsBo getClassDetailsBoFromClassDetailsDao(ClassDetailsDao classDetailsDao) {
        String classDetailsJson = classDetailsDao.getClassDetailsJson();
        if (classDetailsJson == null)
            classDetailsJson = "{}";
        Class cClass = null;
        if (!classDetailsJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            cClass = Class
                    .builder()
                    .build();
            try {
                cClass = objectMapper.readValue(classDetailsJson, Class.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ClassDetailsBo
                .builder()
                .cClass(cClass)
                .build();
    }

    public ClassDetailsDao getClassDetailsDaoFromClassDetailsJson(String classDetailsJson) {
        return ClassDetailsDao
                .builder()
                .classDetailsJson(classDetailsJson)
                .build();
    }
}