package services.worlddetailservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import objects.DungeonMaster;
import objects.Player;
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
        String visibilityJson = worldDetailsAndVisibilityBo.getVisibilityJson();
        World filteredWorld = world;
        if (player.getClass() != DungeonMaster.class) {
            ObjectMapper objectMapper = new ObjectMapper();
            String worldJson = "{}";
            try {
                worldJson = objectMapper.writeValueAsString(world);
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
                if (worldJsonObject.containsKey(visibilityKey) && (!Boolean.parseBoolean(visibilityJsonObject.get(visibilityKey).toString())))
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
                .visibilityJson(visibilityJson)
                .build();
    }

    private WorldDetailsAndVisibilityAndPlayerBo filterWorldDetailsAndVisibilityAndPlayerBo(WorldDetailsAndVisibilityAndPlayerBo worldDetailsAndVisibilityAndPlayerBo) {
        World world = worldDetailsAndVisibilityAndPlayerBo.getWorld();
        String visibilityJson = worldDetailsAndVisibilityAndPlayerBo.getVisibilityJson();
        Player player = worldDetailsAndVisibilityAndPlayerBo.getPlayer();
        if (player.getClass() != DungeonMaster.class) {
            visibilityJson = "{}";
            world = null;
        }
        return WorldDetailsAndVisibilityAndPlayerBo
                .builder()
                .world(world)
                .visibilityJson(visibilityJson)
                .player(player)
                .build();
    }

}