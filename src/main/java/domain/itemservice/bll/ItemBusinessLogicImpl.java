package domain.itemservice.bll;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import common.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import domain.itemservice.bll.bo.ItemAndPlayerBo;
import domain.itemservice.bll.bo.ItemAndVisibilityAndPlayerBo;
import domain.itemservice.bll.bo.ItemAndVisibilityBo;
import domain.itemservice.dal.ItemDataAccess;
import domain.itemservice.dal.ItemDataAccessConverter;
import domain.itemservice.dal.dao.ItemDao;
import domain.itemservice.dal.dao.ItemAndVisibilityDao;
import java.util.Map;

public class ItemBusinessLogicImpl implements ItemBusinessLogic {
    @Inject
    private ItemDataAccessConverter itemDataAccessConverter;
    @Inject
    private ItemDataAccess itemDataAccess;
    @Inject
    private ItemBusinessLogicConverter itemBusinessLogicConverter;

    public ItemAndVisibilityBo getItemAndVisibilityBo(ItemAndPlayerBo itemAndPlayerBo) {
        ItemDao itemDao = itemDataAccessConverter.getItemDaoFromItemAndPlayerBo(itemAndPlayerBo);
        ItemAndVisibilityDao itemAndVisibilityDao = itemDataAccess.getItemAndVisibilityDao(itemDao);
        ItemAndVisibilityBo itemAndVisibilityBo = itemDataAccessConverter.getItemAndVisibilityBoFromItemAndVisibilityDao(itemAndVisibilityDao);
        Player player = itemAndPlayerBo.getPlayer();
        if (itemAndVisibilityBo.getItem() == null)
            return itemAndVisibilityBo;
        return filterItemAndVisibilityBo(itemAndVisibilityBo, player);
    }

    public ItemAndVisibilityBo getItemAndVisibilityBo(ItemAndVisibilityAndPlayerBo itemAndVisibilityAndPlayerBo) {
        ItemAndVisibilityAndPlayerBo filteredItemAndVisibilityAndPlayerBo = filterItemAndVisibilityAndPlayerBo(itemAndVisibilityAndPlayerBo);
        ItemAndVisibilityBo itemAndVisibilityBo = itemBusinessLogicConverter.getItemAndVisibilityBoFromItemAndVisibilityAndPlayerBo(filteredItemAndVisibilityAndPlayerBo);
        ItemAndVisibilityDao itemAndVisibilityDao = itemDataAccessConverter.getItemAndVisibilityDaoFromItemAndVisibilityBo(itemAndVisibilityBo);
        itemAndVisibilityDao = itemDataAccess.getItemAndVisibilityDao(itemAndVisibilityDao);
        return itemDataAccessConverter.getItemAndVisibilityBoFromItemAndVisibilityDao(itemAndVisibilityDao);
    }

    private ItemAndVisibilityBo filterItemAndVisibilityBo(ItemAndVisibilityBo itemAndVisibilityBo, Player player) {
        Item item = itemAndVisibilityBo.getItem();
        Map<String, Visibility> visibilityMap = itemAndVisibilityBo.getVisibilityMap();
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
        return ItemAndVisibilityBo
                .builder()
                .item(filteredItem)
                .visibilityMap(visibilityMap)
                .build();
    }

    private ItemAndVisibilityAndPlayerBo filterItemAndVisibilityAndPlayerBo(ItemAndVisibilityAndPlayerBo itemAndVisibilityAndPlayerBo) {
        Item item = itemAndVisibilityAndPlayerBo.getItem();
        Map<String, Visibility> visibilityMap = itemAndVisibilityAndPlayerBo.getVisibilityMap();
        Player player = itemAndVisibilityAndPlayerBo.getPlayer();
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
        return ItemAndVisibilityAndPlayerBo
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