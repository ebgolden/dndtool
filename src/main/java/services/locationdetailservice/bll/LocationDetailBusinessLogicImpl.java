package services.locationdetailservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import objects.DungeonMaster;
import objects.Location;
import objects.Player;
import objects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.locationdetailservice.bll.bo.LocationAndPlayerBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityAndDungeonMasterBo;
import services.locationdetailservice.bll.bo.LocationDetailsAndVisibilityBo;
import services.locationdetailservice.dal.LocationDetailDataAccess;
import services.locationdetailservice.dal.LocationDetailDataAccessConverter;
import services.locationdetailservice.dal.dao.LocationDao;
import services.locationdetailservice.dal.dao.LocationDetailsAndVisibilityDao;
import java.util.Map;

public class LocationDetailBusinessLogicImpl implements LocationDetailBusinessLogic {
    @Inject
    private LocationDetailDataAccessConverter locationDetailDataAccessConverter;
    @Inject
    private LocationDetailDataAccess locationDetailDataAccess;
    @Inject
    private LocationDetailBusinessLogicConverter locationDetailBusinessLogicConverter;

    public LocationDetailsAndVisibilityBo getLocationDetailsAndVisibilityBo(LocationAndPlayerBo locationAndPlayerBo) {
        LocationDao locationDao = locationDetailDataAccessConverter.getLocationDaoFromLocationAndPlayerBo(locationAndPlayerBo);
        LocationDetailsAndVisibilityDao locationDetailsAndVisibilityDao = locationDetailDataAccess.getLocationDetailsAndVisibilityDao(locationDao);
        LocationDetailsAndVisibilityBo locationDetailsAndVisibilityBo = locationDetailDataAccessConverter.getLocationDetailsAndVisibilityBoFromLocationDetailsAndVisibilityDao(locationDetailsAndVisibilityDao);
        Player player = locationAndPlayerBo.getPlayer();
        if (locationDetailsAndVisibilityBo.getLocation() == null)
            return locationDetailsAndVisibilityBo;
        return filterLocationDetailsAndVisibilityBo(locationDetailsAndVisibilityBo, player);
    }

    public LocationDetailsAndVisibilityBo getLocationDetailsAndVisibilityBo(LocationDetailsAndVisibilityAndDungeonMasterBo locationDetailsAndVisibilityAndDungeonMasterBo) {
        LocationDetailsAndVisibilityAndDungeonMasterBo filteredLocationDetailsAndVisibilityAndDungeonMasterBo = filterLocationDetailsAndVisibilityAndDungeonMasterBo(locationDetailsAndVisibilityAndDungeonMasterBo);
        LocationDetailsAndVisibilityBo locationDetailsAndVisibilityBo = locationDetailBusinessLogicConverter.getLocationDetailsAndVisibilityBoFromLocationDetailsAndVisibilityAndDungeonMasterBo(filteredLocationDetailsAndVisibilityAndDungeonMasterBo);
        LocationDetailsAndVisibilityDao locationDetailsAndVisibilityDao = locationDetailDataAccessConverter.getLocationDetailsAndVisibilityDaoFromLocationDetailsAndVisibilityBo(locationDetailsAndVisibilityBo);
        locationDetailsAndVisibilityDao = locationDetailDataAccess.getLocationDetailsAndVisibilityDao(locationDetailsAndVisibilityDao);
        return locationDetailDataAccessConverter.getLocationDetailsAndVisibilityBoFromLocationDetailsAndVisibilityDao(locationDetailsAndVisibilityDao);
    }

    private LocationDetailsAndVisibilityBo filterLocationDetailsAndVisibilityBo(LocationDetailsAndVisibilityBo locationDetailsAndVisibilityBo, Player player) {
        Location location = locationDetailsAndVisibilityBo.getLocation();
        Map<String, Visibility> visibilityMap = locationDetailsAndVisibilityBo.getVisibilityMap();
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
        return LocationDetailsAndVisibilityBo
                .builder()
                .location(filteredLocation)
                .visibilityMap(visibilityMap)
                .build();
    }

    private LocationDetailsAndVisibilityAndDungeonMasterBo filterLocationDetailsAndVisibilityAndDungeonMasterBo(LocationDetailsAndVisibilityAndDungeonMasterBo locationDetailsAndVisibilityAndDungeonMasterBo) {
        Location location = locationDetailsAndVisibilityAndDungeonMasterBo.getLocation();
        Map<String, Visibility> visibilityMap = locationDetailsAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        DungeonMaster dungeonMaster = locationDetailsAndVisibilityAndDungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        String campaignDungeonMasterId = location.getDungeonMasterId();
        if (!dungeonMasterId.equals(campaignDungeonMasterId)) {
            location = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null)
            visibilityMap.keySet().forEach(key -> correctVisibility(filteredVisibilityMap, key));
        return LocationDetailsAndVisibilityAndDungeonMasterBo
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