package services.worlddetailservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import objects.DungeonMaster;
import objects.Player;
import objects.Visibility;
import objects.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.worlddetailservice.bll.bo.WorldAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;
import services.worlddetailservice.dal.WorldDetailDataAccess;
import services.worlddetailservice.dal.WorldDetailDataAccessConverter;
import services.worlddetailservice.dal.dao.WorldDao;
import services.worlddetailservice.dal.dao.WorldDetailsAndVisibilityDao;
import java.util.Map;

public class WorldDetailBusinessLogicImpl implements WorldDetailBusinessLogic {
    @Inject
    private WorldDetailDataAccessConverter worldDetailDataAccessConverter;
    @Inject
    private WorldDetailDataAccess worldDetailDataAccess;
    @Inject
    private WorldDetailBusinessLogicConverter worldDetailBusinessLogicConverter;

    public WorldDetailsAndVisibilityBo getWorldDetailsAndVisibilityBo(WorldAndPlayerBo worldAndPlayerBo) {
        WorldDao worldDao = worldDetailDataAccessConverter.getWorldDaoFromWorldAndPlayerBo(worldAndPlayerBo);
        WorldDetailsAndVisibilityDao worldDetailsAndVisibilityDao = worldDetailDataAccess.getWorldDetailsAndVisibilityDao(worldDao);
        WorldDetailsAndVisibilityBo worldDetailsAndVisibilityBo = worldDetailDataAccessConverter.getWorldDetailsAndVisibilityBoFromWorldDetailsAndVisibilityDao(worldDetailsAndVisibilityDao);
        Player player = worldAndPlayerBo.getPlayer();
        if (worldDetailsAndVisibilityBo.getWorld() == null)
            return worldDetailsAndVisibilityBo;
        return filterWorldDetailsAndVisibilityBo(worldDetailsAndVisibilityBo, player);
    }

    public WorldDetailsAndVisibilityBo getWorldDetailsAndVisibilityBo(WorldDetailsAndVisibilityAndPlayerBo worldDetailsAndVisibilityAndPlayerBo) {
        WorldDetailsAndVisibilityAndPlayerBo filteredWorldDetailsAndVisibilityAndPlayerBo = filterWorldDetailsAndVisibilityAndPlayerBo(worldDetailsAndVisibilityAndPlayerBo);
        WorldDetailsAndVisibilityBo worldDetailsAndVisibilityBo = worldDetailBusinessLogicConverter.getWorldDetailsAndVisibilityBoFromWorldDetailsAndVisibilityAndPlayerBo(filteredWorldDetailsAndVisibilityAndPlayerBo);
        WorldDetailsAndVisibilityDao worldDetailsAndVisibilityDao = worldDetailDataAccessConverter.getWorldDetailsAndVisibilityDaoFromWorldDetailsAndVisibilityBo(worldDetailsAndVisibilityBo);
        worldDetailsAndVisibilityDao = worldDetailDataAccess.getWorldDetailsAndVisibilityDao(worldDetailsAndVisibilityDao);
        return worldDetailDataAccessConverter.getWorldDetailsAndVisibilityBoFromWorldDetailsAndVisibilityDao(worldDetailsAndVisibilityDao);
    }

    private WorldDetailsAndVisibilityBo filterWorldDetailsAndVisibilityBo(WorldDetailsAndVisibilityBo worldDetailsAndVisibilityBo, Player player) {
        World world = worldDetailsAndVisibilityBo.getWorld();
        Map<String, Visibility> visibilityMap = worldDetailsAndVisibilityBo.getVisibilityMap();
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
        return WorldDetailsAndVisibilityBo
                .builder()
                .world(filteredWorld)
                .visibilityMap(visibilityMap)
                .build();
    }

    private WorldDetailsAndVisibilityAndPlayerBo filterWorldDetailsAndVisibilityAndPlayerBo(WorldDetailsAndVisibilityAndPlayerBo worldDetailsAndVisibilityAndPlayerBo) {
        World world = worldDetailsAndVisibilityAndPlayerBo.getWorld();
        Map<String, Visibility> visibilityMap = worldDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = worldDetailsAndVisibilityAndPlayerBo.getPlayer();
        if (player.getClass() != DungeonMaster.class) {
            world = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null)
            visibilityMap.keySet().forEach(key -> correctVisibility(player, filteredVisibilityMap, key));
        return WorldDetailsAndVisibilityAndPlayerBo
                .builder()
                .world(world)
                .visibilityMap(filteredVisibilityMap)
                .player(player)
                .build();
    }

    private void correctVisibility(Player player, Map<String, Visibility> visibilityMap, String key) {
        if ((player.getClass() == DungeonMaster.class) && (visibilityMap.get(key) == Visibility.PLAYER))
            visibilityMap.replace(key, Visibility.DUNGEON_MASTER);
    }
}