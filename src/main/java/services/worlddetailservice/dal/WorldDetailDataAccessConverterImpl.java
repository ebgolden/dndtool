package services.worlddetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Visibility;
import objects.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.worlddetailservice.bll.bo.WorldAndPlayerBo;
import services.worlddetailservice.bll.bo.WorldDetailsAndVisibilityBo;
import services.worlddetailservice.dal.dao.WorldDao;
import services.worlddetailservice.dal.dao.WorldDetailsAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

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

    public WorldDetailsAndVisibilityDao getWorldDetailsAndVisibilityDaoFromWorldDetailsAndVisibilityBo(WorldDetailsAndVisibilityBo worldDetailsAndVisibilityBo) {
        World world = worldDetailsAndVisibilityBo.getWorld();
        Map<String, Visibility> visibilityMap = worldDetailsAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String worldJson = "{}";
        String visibilityJson = "{}";
        try {
            worldJson = objectMapper.writeValueAsString(world);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String worldDetailsAndVisibilityJson = "{}";
        if ((!worldJson.equals("{}") && (!worldJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            worldDetailsAndVisibilityJson = "{" +
                    "worldDetails:" +
                    worldJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return WorldDetailsAndVisibilityDao
                .builder()
                .worldDetailsAndVisibilityJson(worldDetailsAndVisibilityJson)
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
        ObjectMapper objectMapper = new ObjectMapper();
        String worldDetailsJson;
        World world = null;
        if (jsonObject.get("worldDetails") != null) {
            worldDetailsJson = ((JSONObject) jsonObject.get("worldDetails")).toJSONString();
            world = World
                    .builder()
                    .build();
            try {
                world = objectMapper.readValue(worldDetailsJson, World.class);
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
        return WorldDetailsAndVisibilityBo
                .builder()
                .world(world)
                .visibilityMap(visibilityMap)
                .build();
    }

    public WorldDetailsAndVisibilityDao getWorldDetailsAndVisibilityDaoFromWorldDetailsAndVisibilityJson(String worldDetailsAndVisibilityJson) {
        return WorldDetailsAndVisibilityDao
                .builder()
                .worldDetailsAndVisibilityJson(worldDetailsAndVisibilityJson)
                .build();
    }
}