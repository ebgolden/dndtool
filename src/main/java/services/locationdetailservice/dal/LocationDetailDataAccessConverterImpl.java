package services.locationdetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Location;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.locationdetailservice.bll.bo.LocationAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityBo;
import services.locationdetailservice.dal.dao.LocationDao;
import services.locationdetailservice.dal.dao.LocationDetailsAndVisibilityDao;

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
        String locationDetailsJson;
        Location location = null;
        if (jsonObject.get("locationDetails") != null) {
            locationDetailsJson = ((JSONObject) jsonObject.get("locationDetails")).toJSONString();
            ObjectMapper objectMapper = new ObjectMapper();
            location = Location
                    .builder()
                    .build();
            try {
                location = objectMapper.readValue(locationDetailsJson, Location.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        String visibilityJson = "{}";
        if (jsonObject.get("visibility") != null)
            visibilityJson = ((JSONObject)jsonObject.get("visibility")).toJSONString();
        return LocationDetailsAndVisibilityBo
                .builder()
                .location(location)
                .visibilityJson(visibilityJson)
                .build();
    }

    public LocationDetailsAndVisibilityDao getLocationDetailsAndVisibilityDaoFromUpdatedJsonObject(String updatedJsonObject) {
        return LocationDetailsAndVisibilityDao
                .builder()
                .locationDetailsAndVisibilityJson(updatedJsonObject)
                .build();
    }
}