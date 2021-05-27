package services.locationservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import commonobjects.DungeonMaster;
import commonobjects.Location;
import commonobjects.Player;
import commonobjects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.locationservice.bll.bo.LocationAndPlayerBo;
import services.locationservice.bll.bo.LocationAndVisibilityAndDungeonMasterBo;
import services.locationservice.bll.bo.LocationAndVisibilityBo;
import services.locationservice.dal.LocationDataAccess;
import services.locationservice.dal.LocationDataAccessConverter;
import services.locationservice.dal.dao.LocationDao;
import services.locationservice.dal.dao.LocationAndVisibilityDao;
import java.util.Map;

public class LocationBusinessLogicImpl implements LocationBusinessLogic {
    @Inject
    private LocationDataAccessConverter locationDataAccessConverter;
    @Inject
    private LocationDataAccess locationDataAccess;
    @Inject
    private LocationBusinessLogicConverter locationBusinessLogicConverter;

    public LocationAndVisibilityBo getLocationAndVisibilityBo(LocationAndPlayerBo locationAndPlayerBo) {
        LocationDao locationDao = locationDataAccessConverter.getLocationDaoFromLocationAndPlayerBo(locationAndPlayerBo);
        LocationAndVisibilityDao locationAndVisibilityDao = locationDataAccess.getLocationAndVisibilityDao(locationDao);
        LocationAndVisibilityBo locationAndVisibilityBo = locationDataAccessConverter.getLocationAndVisibilityBoFromLocationAndVisibilityDao(locationAndVisibilityDao);
        Player player = locationAndPlayerBo.getPlayer();
        if (locationAndVisibilityBo.getLocation() == null)
            return locationAndVisibilityBo;
        return filterLocationAndVisibilityBo(locationAndVisibilityBo, player);
    }

    public LocationAndVisibilityBo getLocationAndVisibilityBo(LocationAndVisibilityAndDungeonMasterBo locationAndVisibilityAndDungeonMasterBo) {
        LocationAndVisibilityAndDungeonMasterBo filteredLocationAndVisibilityAndDungeonMasterBo = filterLocationAndVisibilityAndDungeonMasterBo(locationAndVisibilityAndDungeonMasterBo);
        LocationAndVisibilityBo locationAndVisibilityBo = locationBusinessLogicConverter.getLocationAndVisibilityBoFromLocationAndVisibilityAndDungeonMasterBo(filteredLocationAndVisibilityAndDungeonMasterBo);
        LocationAndVisibilityDao locationAndVisibilityDao = locationDataAccessConverter.getLocationAndVisibilityDaoFromLocationAndVisibilityBo(locationAndVisibilityBo);
        locationAndVisibilityDao = locationDataAccess.getLocationAndVisibilityDao(locationAndVisibilityDao);
        return locationDataAccessConverter.getLocationAndVisibilityBoFromLocationAndVisibilityDao(locationAndVisibilityDao);
    }

    private LocationAndVisibilityBo filterLocationAndVisibilityBo(LocationAndVisibilityBo locationAndVisibilityBo, Player player) {
        Location location = locationAndVisibilityBo.getLocation();
        Map<String, Visibility> visibilityMap = locationAndVisibilityBo.getVisibilityMap();
        Location filteredLocation = location;
        if (player.getClass() != DungeonMaster.class) {
            ObjectMapper objectMapper = new ObjectMapper();
            String locationJson = "{}";
            String visibilityJson = "{}";
            try {
                locationJson = objectMapper.writeValueAsString(location);
                visibilityJson = objectMapper.writeValueAsString(visibilityMap);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject locationJsonObject = new JSONObject();
            JSONObject visibilityJsonObject = new JSONObject();
            try {
                locationJsonObject = (JSONObject)jsonParser.parse(locationJson);
                visibilityJsonObject = (JSONObject)jsonParser.parse(visibilityJson);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String[] visibilityKeys = new String[] {};
            visibilityKeys = (String[])visibilityJsonObject
                    .keySet()
                    .toArray(visibilityKeys);
            for (String visibilityKey : visibilityKeys) {
                Visibility visibility = Visibility.valueOf(visibilityJsonObject.get(visibilityKey).toString().toUpperCase());
                if (locationJsonObject.containsKey(visibilityKey) && (visibility != Visibility.EVERYONE))
                    locationJsonObject.remove(visibilityKey);
            }
            JSONObject filteredLocationJsonObject = locationJsonObject;
            String filteredLocationJson = filteredLocationJsonObject.toJSONString();
            try {
                filteredLocation = objectMapper.readValue(filteredLocationJson, Location.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return LocationAndVisibilityBo
                .builder()
                .location(filteredLocation)
                .visibilityMap(visibilityMap)
                .build();
    }

    private LocationAndVisibilityAndDungeonMasterBo filterLocationAndVisibilityAndDungeonMasterBo(LocationAndVisibilityAndDungeonMasterBo locationAndVisibilityAndDungeonMasterBo) {
        Location location = locationAndVisibilityAndDungeonMasterBo.getLocation();
        Map<String, Visibility> visibilityMap = locationAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        DungeonMaster dungeonMaster = locationAndVisibilityAndDungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        String campaignDungeonMasterId = location.getDungeonMasterId();
        if (!dungeonMasterId.equals(campaignDungeonMasterId)) {
            location = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null)
            visibilityMap.keySet().forEach(key -> correctVisibility(filteredVisibilityMap, key));
        return LocationAndVisibilityAndDungeonMasterBo
                .builder()
                .location(location)
                .visibilityMap(filteredVisibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    private void correctVisibility(Map<String, Visibility> visibilityMap, String key) {
        if (visibilityMap.get(key) == Visibility.PLAYER)
            visibilityMap.replace(key, Visibility.DUNGEON_MASTER);
    }
}