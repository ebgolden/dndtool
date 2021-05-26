package services.resultdetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Result;
import objects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.resultdetailservice.bll.bo.ResultAndPlayerBo;
import services.resultdetailservice.bll.bo.ResultDetailsAndVisibilityBo;
import services.resultdetailservice.dal.dao.ResultDao;
import services.resultdetailservice.dal.dao.ResultDetailsAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class ResultDetailDataAccessConverterImpl implements ResultDetailDataAccessConverter {
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

    public ResultDetailsAndVisibilityDao getResultDetailsAndVisibilityDaoFromResultDetailsAndVisibilityBo(ResultDetailsAndVisibilityBo resultDetailsAndVisibilityBo) {
        Result result = resultDetailsAndVisibilityBo.getResult();
        Map<String, Visibility> visibilityMap = resultDetailsAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String resultJson = "{}";
        String visibilityJson = "{}";
        try {
            resultJson = objectMapper.writeValueAsString(result);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String resultDetailsAndVisibilityJson = "{}";
        if ((!resultJson.equals("{}") && (!resultJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            resultDetailsAndVisibilityJson = "{" +
                    "resultDetails:" +
                    resultJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return ResultDetailsAndVisibilityDao
                .builder()
                .resultDetailsAndVisibilityJson(resultDetailsAndVisibilityJson)
                .build();
    }

    public ResultDetailsAndVisibilityBo getResultDetailsAndVisibilityBoFromResultDetailsAndVisibilityDao(ResultDetailsAndVisibilityDao resultDetailsAndVisibilityDao) {
        String resultDetailsAndVisibilityJson = resultDetailsAndVisibilityDao.getResultDetailsAndVisibilityJson();
        if (resultDetailsAndVisibilityJson == null)
            resultDetailsAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(resultDetailsAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String resultDetailsJson;
        Result result  = null;
        if (jsonObject.get("resultDetails") != null) {
            resultDetailsJson = ((JSONObject) jsonObject.get("resultDetails")).toJSONString();
            result = Result
                    .builder()
                    .build();
            try {
                result = objectMapper.readValue(resultDetailsJson, Result.class);
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
                visibilityMap = objectMapper.readValue(visibilityJson, Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ResultDetailsAndVisibilityBo
                .builder()
                .result(result)
                .visibilityMap(visibilityMap)
                .build();
    }

    public ResultDetailsAndVisibilityDao getResultDetailsAndVisibilityDaoFromResultDetailsAndVisibilityJson(String resultDetailsAndVisibilityJson) {
        return ResultDetailsAndVisibilityDao
                .builder()
                .resultDetailsAndVisibilityJson(resultDetailsAndVisibilityJson)
                .build();
    }
}