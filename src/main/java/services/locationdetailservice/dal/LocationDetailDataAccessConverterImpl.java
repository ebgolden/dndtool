package services.locationdetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Location;
import objects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.locationdetailservice.bll.bo.LocationAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityBo;
import services.locationdetailservice.dal.dao.LocationDao;
import services.locationdetailservice.dal.dao.LocationDetailsAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class LocationDetailDataAccessConverterImpl implements LocationDetailDataAccessConverter {
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

    public LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDaoFromLocationDetailsAndVisibilityBo(LocationDetailsAndVisibilityBo locationDetailsAndVisibilityBo) {
        Location location = locationDetailsAndVisibilityBo.getLocation();
        Map<String, Visibility> visibilityMap = locationDetailsAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String locationJson = "{}";
        String visibilityJson = "{}";
        try {
            locationJson = objectMapper.writeValueAsString(location);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String locationDetailsAndVisibilityJson = "{}";
        if ((!locationJson.equals("{}") && (!locationJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            locationDetailsAndVisibilityJson = "{" +
                    "locationDetails:" +
                    locationJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return LocationDetailsAndVisibilityDao
                .builder()
                .locationDetailsAndVisibilityJson(locationDetailsAndVisibilityJson)
                .build();
    }

    public LocationDetailsAndVisibilityBo getLocationDetailsAndVisibilityBoFromLocationDetailsAndVisibilityDao(LocationDetailsAndVisibilityDao locationDetailsAndVisibilityDao) {
        String locationDetailsAndVisibilityJson = locationDetailsAndVisibilityDao.getLocationDetailsAndVisibilityJson();
        if (locationDetailsAndVisibilityJson == null)
            locationDetailsAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(locationDetailsAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String locationDetailsJson;
        Location location = null;
        if (jsonObject.get("locationDetails") != null) {
            locationDetailsJson = ((JSONObject) jsonObject.get("locationDetails")).toJSONString();
            location = Location
                    .builder()
                    .build();
            try {
                location = objectMapper.readValue(locationDetailsJson, Location.class);
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
        return LocationDetailsAndVisibilityBo
                .builder()
                .location(location)
                .visibilityMap(visibilityMap)
                .build();
    }

    public LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDaoFromLocationDetailsAndVisibilityJson(String locationDetailsAndVisibilityJson) {
        return LocationDetailsAndVisibilityDao
                .builder()
                .locationDetailsAndVisibilityJson(locationDetailsAndVisibilityJson)
                .build();
    }
}