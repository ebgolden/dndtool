package services.worlddetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.worlddetailservice.bll.bo.WorldAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;
import services.worlddetailservice.dal.dao.WorldDao;
import services.worlddetailservice.dal.dao.WorldDetailsAndVisibilityDao;

public class WorldDetailDataAccessConverterImpl implements WorldDetailDataAccessConverter {
    public WorldDao getWorldDaoFromWorldAndPlayerBo(WorldAndPlayerBo worldAndPlayerBo) {
        World world = worldAndPlayerBo.getWorld();
        ObjectMapper objectMapper = new ObjectMapper();
        String worldJson = "{}";
        try {
            worldJson = objectMapper.writeValueAsString(world);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return WorldDao
                .builder()
                .worldJson(worldJson)
                .build();
    }

    public WorldDetailsAndVisibilityBo getWorldDetailsAndVisibilityBoFromWorldDetailsAndVisibilityDao(WorldDetailsAndVisibilityDao worldDetailsAndVisibilityDao) {
        String worldDetailsAndVisibilityJson = worldDetailsAndVisibilityDao.getWorldDetailsAndVisibilityJson();
        if (worldDetailsAndVisibilityJson == null)
            worldDetailsAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(worldDetailsAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String worldDetailsJson;
        World world = null;
        if (jsonObject.get("worldDetails") != null) {
            worldDetailsJson = ((JSONObject) jsonObject.get("worldDetails")).toJSONString();
            ObjectMapper objectMapper = new ObjectMapper();
            world = World
                    .builder()
                    .build();
            try {
                world = objectMapper.readValue(worldDetailsJson, World.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        String visibilityJson = "{}";
        if (jsonObject.get("visibility") != null)
            visibilityJson = ((JSONObject)jsonObject.get("visibility")).toJSONString();
        return WorldDetailsAndVisibilityBo
                .builder()
                .world(world)
                .visibilityJson(visibilityJson)
                .build();
    }

    public WorldDetailsAndVisibilityDao getWorldDetailsAndVisibilityDaoFromLatestJsonObject(String latestJsonObject) {
        return WorldDetailsAndVisibilityDao
                .builder()
                .worldDetailsAndVisibilityJson(latestJsonObject)
                .build();
    }
}