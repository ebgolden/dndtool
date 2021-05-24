package services.itemdetailservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import objects.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.itemdetailservice.bll.bo.ItemAndPlayerBo;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityAndPlayerBo;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityBo;
import services.itemdetailservice.dal.ItemDetailDataAccess;
import services.itemdetailservice.dal.ItemDetailDataAccessConverter;
import services.itemdetailservice.dal.dao.ItemDao;
import services.itemdetailservice.dal.dao.ItemDetailsAndVisibilityDao;
import java.util.Map;

public class ItemDetailBusinessLogicImpl implements ItemDetailBusinessLogic {
    @Inject
    private ItemDetailDataAccessConverter itemDetailDataAccessConverter;
    @Inject
    private ItemDetailDataAccess itemDetailDataAccess;
    @Inject
    private ItemDetailBusinessLogicConverter itemDetailBusinessLogicConverter;

    public ItemDetailsAndVisibilityBo getItemDetailsAndVisibilityBo(ItemAndPlayerBo itemAndPlayerBo) {
        ItemDao itemDao = itemDetailDataAccessConverter.getItemDaoFromItemAndPlayerBo(itemAndPlayerBo);
        ItemDetailsAndVisibilityDao itemDetailsAndVisibilityDao = itemDetailDataAccess.getItemDetailsAndVisibilityDao(itemDao);
        ItemDetailsAndVisibilityBo itemDetailsAndVisibilityBo = itemDetailDataAccessConverter.getItemDetailsAndVisibilityBoFromItemDetailsAndVisibilityDao(itemDetailsAndVisibilityDao);
        Player player = itemAndPlayerBo.getPlayer();
        if (itemDetailsAndVisibilityBo.getItem() == null)
            return itemDetailsAndVisibilityBo;
        return filterItemDetailsAndVisibilityBo(itemDetailsAndVisibilityBo, player);
    }

    public ItemDetailsAndVisibilityBo getItemDetailsAndVisibilityBo(ItemDetailsAndVisibilityAndPlayerBo itemDetailsAndVisibilityAndPlayerBo) {
        ItemDetailsAndVisibilityAndPlayerBo filteredItemDetailsAndVisibilityAndPlayerBo = filterItemDetailsAndVisibilityAndPlayerBo(itemDetailsAndVisibilityAndPlayerBo);
        ItemDetailsAndVisibilityBo itemDetailsAndVisibilityBo = itemDetailBusinessLogicConverter.getItemDetailsAndVisibilityBoFromItemDetailsAndVisibilityAndPlayerBo(filteredItemDetailsAndVisibilityAndPlayerBo);
        ItemDetailsAndVisibilityDao itemDetailsAndVisibilityDao = itemDetailDataAccessConverter.getItemDetailsAndVisibilityDaoFromItemDetailsAndVisibilityBo(itemDetailsAndVisibilityBo);
        itemDetailsAndVisibilityDao = itemDetailDataAccess.getItemDetailsAndVisibilityDao(itemDetailsAndVisibilityDao);
        return itemDetailDataAccessConverter.getItemDetailsAndVisibilityBoFromItemDetailsAndVisibilityDao(itemDetailsAndVisibilityDao);
    }

    private ItemDetailsAndVisibilityBo filterItemDetailsAndVisibilityBo(ItemDetailsAndVisibilityBo itemDetailsAndVisibilityBo, Player player) {
        Item item = itemDetailsAndVisibilityBo.getItem();
        Map<String, Visibility> visibilityMap = itemDetailsAndVisibilityBo.getVisibilityMap();
        String playerId = player.getId();
        String itemPlayerId = item.getPlayerId();
        Item filteredItem = item;
        if (player.getClass() != DungeonMaster.class) {
            ObjectMapper objectMapper = new ObjectMapper();
            String itemJson = "{}";
            String visibilityJson = "{}";
            try {
                itemJson = objectMapper.writeValueAsString(item);
                visibilityJson = objectMapper.writeValueAsString(visibilityMap);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject itemJsonObject = new JSONObject();
            JSONObject visibilityJsonObject = new JSONObject();
            try {
                itemJsonObject = (JSONObject)jsonParser.parse(itemJson);
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
                if (itemJsonObject.containsKey(visibilityKey) &&
                        (((!playerId.equals(itemPlayerId)) && (visibility != Visibility.EVERYONE)) ||
                                ((playerId.equals(itemPlayerId)) && (visibility == Visibility.DUNGEON_MASTER))))
                    itemJsonObject.remove(visibilityKey);
            }
            JSONObject filteredItemJsonObject = itemJsonObject;
            String filteredItemJson = filteredItemJsonObject.toJSONString();
            try {
                filteredItem = objectMapper.readValue(filteredItemJson, Item.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ItemDetailsAndVisibilityBo
                .builder()
                .item(filteredItem)
                .visibilityMap(visibilityMap)
                .build();
    }

    private ItemDetailsAndVisibilityAndPlayerBo filterItemDetailsAndVisibilityAndPlayerBo(ItemDetailsAndVisibilityAndPlayerBo itemDetailsAndVisibilityAndPlayerBo) {
        Item item = itemDetailsAndVisibilityAndPlayerBo.getItem();
        Map<String, Visibility> visibilityMap = itemDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = itemDetailsAndVisibilityAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String itemPlayerId = item.getPlayerId();
        if (!playerId.equals(itemPlayerId) && (player.getClass() != DungeonMaster.class)) {
            item = null;
            visibilityMap = null;
        }
        Map<String, Visibility> filteredVisibilityMap = visibilityMap;
        if (filteredVisibilityMap != null) {
            Item finalItem = item;
            visibilityMap.keySet().forEach(key -> correctVisibility(player, itemPlayerId, filteredVisibilityMap, key, finalItem));
        }
        return ItemDetailsAndVisibilityAndPlayerBo
                .builder()
                .item(item)
                .visibilityMap(filteredVisibilityMap)
                .player(player)
                .build();
    }

    private void correctVisibility(Player player, String itemPlayerId, Map<String, Visibility> visibilityMap, String key, Item item) {
        String playerId = player.getId();
        ObjectMapper objectMapper = new ObjectMapper();
        String itemJson = "{}";
        try {
            itemJson = objectMapper.writeValueAsString(item);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject itemJsonObject = new JSONObject();
        try {
            itemJsonObject = (JSONObject)jsonParser.parse(itemJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!itemJsonObject.containsKey(key))
            visibilityMap.remove(key);
        else if ((player.getClass() != DungeonMaster.class) && (visibilityMap.get(key) == Visibility.DUNGEON_MASTER))
            visibilityMap.replace(key, Visibility.PLAYER);
        else if (playerId.equals(itemPlayerId) && (player.getClass() == DungeonMaster.class) && (visibilityMap.get(key) == Visibility.PLAYER))
            visibilityMap.replace(key, Visibility.DUNGEON_MASTER);
    }
}