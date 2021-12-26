package com.ebgolden.domain.worldservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebgolden.common.Visibility;
import com.ebgolden.common.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndPlayerBo;
import com.ebgolden.domain.worldservice.bll.bo.WorldAndVisibilityBo;
import com.ebgolden.domain.worldservice.dal.dao.WorldDao;
import com.ebgolden.domain.worldservice.dal.dao.WorldAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class WorldDataAccessConverterImpl implements WorldDataAccessConverter {
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

    public WorldAndVisibilityDao getWorldAndVisibilityDaoFromWorldAndVisibilityBo(WorldAndVisibilityBo worldAndVisibilityBo) {
        World world = worldAndVisibilityBo.getWorld();
        Map<String, Visibility> visibilityMap = worldAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String worldJson = "{}";
        String visibilityJson = "{}";
        try {
            worldJson = objectMapper.writeValueAsString(world);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String worldAndVisibilityJson = "{}";
        if ((!worldJson.equals("{}") && (!worldJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            worldAndVisibilityJson = "{" +
                    "world:" +
                    worldJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return WorldAndVisibilityDao
                .builder()
                .worldAndVisibilityJson(worldAndVisibilityJson)
                .build();
    }

    public WorldAndVisibilityBo getWorldAndVisibilityBoFromWorldAndVisibilityDao(WorldAndVisibilityDao worldAndVisibilityDao) {
        String worldAndVisibilityJson = worldAndVisibilityDao.getWorldAndVisibilityJson();
        if (worldAndVisibilityJson == null)
            worldAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(worldAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String worldJson;
        World world = null;
        if (jsonObject.get("world") != null) {
            worldJson = ((JSONObject) jsonObject.get("world")).toJSONString();
            world = World
                    .builder()
                    .build();
            try {
                world = objectMapper.readValue(worldJson, World.class);
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
        return WorldAndVisibilityBo
                .builder()
                .world(world)
                .visibilityMap(visibilityMap)
                .build();
    }

    public WorldAndVisibilityDao getWorldAndVisibilityDaoFromWorldAndVisibilityJson(String worldAndVisibilityJson) {
        return WorldAndVisibilityDao
                .builder()
                .worldAndVisibilityJson(worldAndVisibilityJson)
                .build();
    }
}