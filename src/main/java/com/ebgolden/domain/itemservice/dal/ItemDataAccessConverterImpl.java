package com.ebgolden.domain.itemservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebgolden.common.Item;
import com.ebgolden.common.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.ebgolden.domain.itemservice.bll.bo.ItemAndPlayerBo;
import com.ebgolden.domain.itemservice.bll.bo.ItemAndVisibilityBo;
import com.ebgolden.domain.itemservice.dal.dao.ItemDao;
import com.ebgolden.domain.itemservice.dal.dao.ItemAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class ItemDataAccessConverterImpl implements ItemDataAccessConverter {
    public ItemDao getItemDaoFromItemAndPlayerBo(ItemAndPlayerBo itemAndPlayerBo) {
        Item item = itemAndPlayerBo.getItem();
        ObjectMapper objectMapper = new ObjectMapper();
        String itemJson = "{}";
        try {
            itemJson = objectMapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ItemDao
                .builder()
                .itemJson(itemJson)
                .build();
    }

    public ItemAndVisibilityDao getItemAndVisibilityDaoFromItemAndVisibilityBo(ItemAndVisibilityBo itemAndVisibilityBo) {
        Item item = itemAndVisibilityBo.getItem();
        Map<String, Visibility> visibilityMap = itemAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String itemJson = "{}";
        String visibilityJson = "{}";
        try {
            itemJson = objectMapper.writeValueAsString(item);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String itemAndVisibilityJson = "{}";
        if ((!itemJson.equals("{}") && (!itemJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            itemAndVisibilityJson = "{" +
                    "item:" +
                    itemJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return ItemAndVisibilityDao
                .builder()
                .itemAndVisibilityJson(itemAndVisibilityJson)
                .build();
    }

    public ItemAndVisibilityBo getItemAndVisibilityBoFromItemAndVisibilityDao(ItemAndVisibilityDao itemAndVisibilityDao) {
        String itemAndVisibilityJson = itemAndVisibilityDao.getItemAndVisibilityJson();
        if (itemAndVisibilityJson == null)
            itemAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(itemAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String itemJson;
        Item item = null;
        if (jsonObject.get("item") != null) {
            itemJson = ((JSONObject) jsonObject.get("item")).toJSONString();
            item = Item
                    .builder()
                    .build();
            try {
                item = objectMapper.readValue(itemJson, Item.class);
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
        return ItemAndVisibilityBo
                .builder()
                .item(item)
                .visibilityMap(visibilityMap)
                .build();
    }

    public ItemAndVisibilityDao getItemAndVisibilityDaoFromItemAndVisibilityJson(String itemAndVisibilityJson) {
        return ItemAndVisibilityDao
                .builder()
                .itemAndVisibilityJson(itemAndVisibilityJson)
                .build();
    }
}