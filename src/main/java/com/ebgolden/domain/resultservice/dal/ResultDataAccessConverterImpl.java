package com.ebgolden.domain.resultservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebgolden.common.Result;
import com.ebgolden.common.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.ebgolden.domain.resultservice.bll.bo.ResultAndPlayerBo;
import com.ebgolden.domain.resultservice.bll.bo.ResultAndVisibilityBo;
import com.ebgolden.domain.resultservice.dal.dao.ResultDao;
import com.ebgolden.domain.resultservice.dal.dao.ResultAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class ResultDataAccessConverterImpl implements ResultDataAccessConverter {
    public ResultDao getResultDaoFromResultAndPlayerBo(ResultAndPlayerBo resultAndPlayerBo) {
        Result result = resultAndPlayerBo.getResult();
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = "{}";
        try {
            resultJson = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResultDao
                .builder()
                .resultJson(resultJson)
                .build();
    }

    public ResultAndVisibilityDao getResultAndVisibilityDaoFromResultAndVisibilityBo(ResultAndVisibilityBo resultAndVisibilityBo) {
        Result result = resultAndVisibilityBo.getResult();
        Map<String, Visibility> visibilityMap = resultAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = "{}";
        String visibilityJson = "{}";
        try {
            resultJson = objectMapper.writeValueAsString(result);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String resultAndVisibilityJson = "{}";
        if ((!resultJson.equals("{}") && (!resultJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            resultAndVisibilityJson = "{" +
                    "result:" +
                    resultJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return ResultAndVisibilityDao
                .builder()
                .resultAndVisibilityJson(resultAndVisibilityJson)
                .build();
    }

    public ResultAndVisibilityBo getResultAndVisibilityBoFromResultAndVisibilityDao(ResultAndVisibilityDao resultAndVisibilityDao) {
        String resultAndVisibilityJson = resultAndVisibilityDao.getResultAndVisibilityJson();
        if (resultAndVisibilityJson == null)
            resultAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(resultAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson;
        Result result  = null;
        if (jsonObject.get("result") != null) {
            resultJson = ((JSONObject) jsonObject.get("result")).toJSONString();
            result = Result
                    .builder()
                    .build();
            try {
                result = objectMapper.readValue(resultJson, Result.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        String visibilityJson;
        Map<String, Visibility> visibilityMap = null;
        if (jsonObject.get("visibility") != null) {
            visibilityJson = ((JSONObject)jsonObject.get("visibility")).toJSONString();
            visibilityMap = new HashMap<>();
            try {
                TypeReference<Map<String, Visibility>> visibilityMapTypeReference = new TypeReference<Map<String, Visibility>>(){};
                visibilityMap = objectMapper.readValue(visibilityJson, visibilityMapTypeReference);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ResultAndVisibilityBo
                .builder()
                .result(result)
                .visibilityMap(visibilityMap)
                .build();
    }

    public ResultAndVisibilityDao getResultAndVisibilityDaoFromResultAndVisibilityJson(String resultAndVisibilityJson) {
        return ResultAndVisibilityDao
                .builder()
                .resultAndVisibilityJson(resultAndVisibilityJson)
                .build();
    }
}