package services.itemdetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Item;
import objects.Visibility;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.itemdetailservice.bll.bo.ItemAndPlayerBo;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityBo;
import services.itemdetailservice.dal.dao.ItemDao;
import services.itemdetailservice.dal.dao.ItemDetailsAndVisibilityDao;
import java.util.HashMap;
import java.util.Map;

public class ItemDetailDataAccessConverterImpl implements ItemDetailDataAccessConverter {
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

    public ItemDetailsAndVisibilityDao getItemDetailsAndVisibilityDaoFromItemDetailsAndVisibilityBo(ItemDetailsAndVisibilityBo itemDetailsAndVisibilityBo) {
        Item item = itemDetailsAndVisibilityBo.getItem();
        Map<String, Visibility> visibilityMap = itemDetailsAndVisibilityBo.getVisibilityMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String itemJson = "{}";
        String visibilityJson = "{}";
        try {
            itemJson = objectMapper.writeValueAsString(item);
            visibilityJson = objectMapper.writeValueAsString(visibilityMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String itemDetailsAndVisibilityJson = "{}";
        if ((!itemJson.equals("{}") && (!itemJson.equals("null"))) || (!visibilityJson.equals("{}") && (!visibilityJson.equals("null"))))
            itemDetailsAndVisibilityJson = "{" +
                    "itemDetails:" +
                    itemJson +
                    ",visibility:" +
                    visibilityJson +
                    "}";
        return ItemDetailsAndVisibilityDao
                .builder()
                .itemDetailsAndVisibilityJson(itemDetailsAndVisibilityJson)
                .build();
    }

    public ItemDetailsAndVisibilityBo getItemDetailsAndVisibilityBoFromItemDetailsAndVisibilityDao(ItemDetailsAndVisibilityDao itemDetailsAndVisibilityDao) {
        String itemDetailsAndVisibilityJson = itemDetailsAndVisibilityDao.getItemDetailsAndVisibilityJson();
        if (itemDetailsAndVisibilityJson == null)
            itemDetailsAndVisibilityJson = "{}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(itemDetailsAndVisibilityJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String itemDetailsJson;
        Item item = null;
        if (jsonObject.get("itemDetails") != null) {
            itemDetailsJson = ((JSONObject) jsonObject.get("itemDetails")).toJSONString();
            item = Item
                    .builder()
                    .build();
            try {
                item = objectMapper.readValue(itemDetailsJson, Item.class);
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
                visibilityMap = objectMapper.readValue(visibilityJson, Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ItemDetailsAndVisibilityBo
                .builder()
                .item(item)
                .visibilityMap(visibilityMap)
                .build();
    }

    public ItemDetailsAndVisibilityDao getItemDetailsAndVisibilityDaoFromItemDetailsAndVisibilityJson(String itemDetailsAndVisibilityJson) {
        return ItemDetailsAndVisibilityDao
                .builder()
                .itemDetailsAndVisibilityJson(itemDetailsAndVisibilityJson)
                .build();
    }
}