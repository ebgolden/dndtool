package com.ebgolden.domain.locationservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebgolden.common.Location;
import com.ebgolden.common.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.ebgolden.domain.locationservice.bll.bo.LocationAndPlayerBo;
import com.ebgolden.domain.locationservice.bll.bo.LocationAndVisibilityBo;
import com.ebgolden.domain.locationservice.dal.dao.LocationDao;
import com.ebgolden.domain.locationservice.dal.dao.LocationAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class LocationDataAccessConverterImpl implements LocationDataAccessConverter {
    public LocationDao getLocationDaoFromLocationAndPlayerBo(LocationAndPlayerBo locationAndPlayerBo) {
        Location location = locationAndPlayerBo.getLocation();
        ObjectMapper objectMapper = new ObjectMapper();
        String locationJson = "{}";
        try {
            locationJson = objectMapper.writeValueAsString(location);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return LocationDao
                .builder()
                .locationJson(locationJson)
                .build();
    }

    public LocationAndVisibilityDao getLocationAndVisibilityDaoFromLocationAndVisibilityBo(LocationAndVisibilityBo locationAndVisibilityBo) {
        Location location = locationAndVisibilityBo.getLocation();
        Map<String, Visibility> visibilityMap = locationAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String locationJson = "{}";
        String visibilityJson = "{}";
        try {
            locationJson = objectMapper.writeValueAsString(location);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String locationAndVisibilityJson = "{}";
        if ((!locationJson.equals("{}") && (!locationJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            locationAndVisibilityJson = "{" +
                    "location:" +
                    locationJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return LocationAndVisibilityDao
                .builder()
                .locationAndVisibilityJson(locationAndVisibilityJson)
                .build();
    }

    public LocationAndVisibilityBo getLocationAndVisibilityBoFromLocationAndVisibilityDao(LocationAndVisibilityDao locationAndVisibilityDao) {
        String locationAndVisibilityJson = locationAndVisibilityDao.getLocationAndVisibilityJson();
        if (locationAndVisibilityJson == null)
            locationAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(locationAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String locationJson;
        Location location = null;
        if (jsonObject.get("location") != null) {
            locationJson = ((JSONObject) jsonObject.get("location")).toJSONString();
            location = Location
                    .builder()
                    .build();
            try {
                location = objectMapper.readValue(locationJson, Location.class);
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
        return LocationAndVisibilityBo
                .builder()
                .location(location)
                .visibilityMap(visibilityMap)
                .build();
    }

    public LocationAndVisibilityDao getLocationAndVisibilityDaoFromLocationAndVisibilityJson(String locationAndVisibilityJson) {
        return LocationAndVisibilityDao
                .builder()
                .locationAndVisibilityJson(locationAndVisibilityJson)
                .build();
    }
}