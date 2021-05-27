package services.worldservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import commonobjects.DungeonMaster;
import commonobjects.Player;
import commonobjects.Visibility;
import commonobjects.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.worldservice.bll.bo.WorldAndPlayerBo;
import services.worldservice.bll.bo.WorldAndVisibilityAndDungeonMasterBo;
import services.worldservice.bll.bo.WorldAndVisibilityBo;
import services.worldservice.dal.WorldDataAccess;
import services.worldservice.dal.WorldDataAccessConverter;
import services.worldservice.dal.dao.WorldDao;
import services.worldservice.dal.dao.WorldAndVisibilityDao;
import java.util.Map;

public class WorldBusinessLogicImpl implements WorldBusinessLogic {
    @Inject
    private WorldDataAccessConverter worldDataAccessConverter;
    @Inject
    private WorldDataAccess worldDataAccess;
    @Inject
    private WorldBusinessLogicConverter worldBusinessLogicConverter;

    public WorldAndVisibilityBo getWorldAndVisibilityBo(WorldAndPlayerBo worldAndPlayerBo) {
        WorldDao worldDao = worldDataAccessConverter.getWorldDaoFromWorldAndPlayerBo(worldAndPlayerBo);
        WorldAndVisibilityDao worldAndVisibilityDao = worldDataAccess.getWorldAndVisibilityDao(worldDao);
        WorldAndVisibilityBo worldAndVisibilityBo = worldDataAccessConverter.getWorldAndVisibilityBoFromWorldAndVisibilityDao(worldAndVisibilityDao);
        Player player = worldAndPlayerBo.getPlayer();
        if (worldAndVisibilityBo.getWorld() == null)
            return worldAndVisibilityBo;
        return filterWorldAndVisibilityBo(worldAndVisibilityBo, player);
    }

    public WorldAndVisibilityBo getWorldAndVisibilityBo(WorldAndVisibilityAndDungeonMasterBo worldAndVisibilityAndDungeonMasterBo) {
        WorldAndVisibilityAndDungeonMasterBo filteredWorldAndVisibilityAndDungeonMasterBo = filterWorldAndVisibilityAndDungeonMasterBo(worldAndVisibilityAndDungeonMasterBo);
        WorldAndVisibilityBo worldAndVisibilityBo = worldBusinessLogicConverter.getWorldAndVisibilityBoFromWorldAndVisibilityAndDungeonMasterBo(filteredWorldAndVisibilityAndDungeonMasterBo);
        WorldAndVisibilityDao worldAndVisibilityDao = worldDataAccessConverter.getWorldAndVisibilityDaoFromWorldAndVisibilityBo(worldAndVisibilityBo);
        worldAndVisibilityDao = worldDataAccess.getWorldAndVisibilityDao(worldAndVisibilityDao);
        return worldDataAccessConverter.getWorldAndVisibilityBoFromWorldAndVisibilityDao(worldAndVisibilityDao);
    }

    private WorldAndVisibilityBo filterWorldAndVisibilityBo(WorldAndVisibilityBo worldAndVisibilityBo, Player player) {
        World world = worldAndVisibilityBo.getWorld();
        Map<String, Visibility> visibilityMap = worldAndVisibilityBo.getVisibilityMap();
        World filteredWorld = world;
        if (player.getClass() != DungeonMaster.class) {
            ObjectMapper objectMapper = new ObjectMapper();
            String worldJson = "{}";
            String visibilityJson = "{}";
            try {
                worldJson = objectMapper.writeValueAsString(world);
                visibilityJson = objectMapper.writeValueAsString(visibilityMap);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject worldJsonObject = new JSONObject();
            JSONObject visibilityJsonObject = new JSONObject();
            try {
                worldJsonObject = (JSONObject)jsonParser.parse(worldJson);
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
                if (worldJsonObject.containsKey(visibilityKey) && (visibility != Visibility.EVERYONE))
                    worldJsonObject.remove(visibilityKey);
            }
            JSONObject filteredWorldJsonObject = worldJsonObject;
            String filteredWorldJson = filteredWorldJsonObject.toJSONString();
            try {
                filteredWorld = objectMapper.readValue(filteredWorldJson, World.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return WorldAndVisibilityBo
                .builder()
                .world(filteredWorld)
                .visibilityMap(visibilityMap)
                .build();
    }

    private WorldAndVisibilityAndDungeonMasterBo filterWorldAndVisibilityAndDungeonMasterBo(WorldAndVisibilityAndDungeonMasterBo worldAndVisibilityAndDungeonMasterBo) {
        World world = worldAndVisibilityAndDungeonMasterBo.getWorld();
        Map<String, Visibility> visibilityMap = worldAndVisibilityAndDungeonMasterBo.getVisibilityMap();
        DungeonMaster dungeonMaster = worldAndVisibilityAndDungeonMasterBo.getDungeonMaster();
        String dungeonMasterId = dungeonMaster.getId();
        String worldDungeonMasterId = world.getDungeonMasterId();
        if (!dungeonMasterId.equals(worldDungeonMasterId)) {
            world = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null)
            visibilityMap.keySet().forEach(key -> correctVisibility(filteredVisibilityMap, key));
        return WorldAndVisibilityAndDungeonMasterBo
                .builder()
                .world(world)
                .visibilityMap(filteredVisibilityMap)
                .dungeonMaster(dungeonMaster)
                .build();
    }

    private void correctVisibility(Map<String, Visibility> visibilityMap, String key) {
        if (visibilityMap.get(key) == Visibility.PLAYER)
            visibilityMap.replace(key, Visibility.DUNGEON_MASTER);
    }
}