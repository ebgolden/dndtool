package services.itemservice.bll;

import commonobjects.Item;
import commonobjects.Player;
import commonobjects.Visibility;
import services.itemservice.UpdatedItemRequest;
import services.itemservice.UpdatedItemResponse;
import services.itemservice.ChangeVisibilityOfItemDetailsRequest;
import services.itemservice.ChangeVisibilityOfItemDetailsResponse;
import services.itemservice.bll.bo.ItemAndPlayerBo;
import services.itemservice.bll.bo.ItemAndVisibilityAndPlayerBo;
import services.itemservice.bll.bo.ItemAndVisibilityBo;
import java.util.Map;

public class ItemBusinessLogicConverterImpl implements ItemBusinessLogicConverter {
    public ItemAndPlayerBo getItemAndPlayerBoFromUpdatedItemRequest(UpdatedItemRequest updatedItemRequest) {
        Item item = updatedItemRequest.getItem();
        Player player = updatedItemRequest.getPlayer();
        return ItemAndPlayerBo
                .builder()
                .item(item)
                .player(player)
                .build();
    }

    public ItemAndVisibilityAndPlayerBo getItemAndVisibilityAndPlayerBoFromChangeVisibilityOfItemDetailsRequest(ChangeVisibilityOfItemDetailsRequest changeVisibilityOfUpdatedItemRequest) {
        Item item = changeVisibilityOfUpdatedItemRequest.getItem();
        Map<String, Visibility> visibilityMap = changeVisibilityOfUpdatedItemRequest.getVisibilityMap();
        Player player = changeVisibilityOfUpdatedItemRequest.getPlayer();
        return ItemAndVisibilityAndPlayerBo
                .builder()
                .item(item)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public UpdatedItemResponse getUpdatedItemResponseFromItemAndVisibilityBo(ItemAndVisibilityBo itemAndVisibilityBo) {
        Item item = itemAndVisibilityBo.getItem();
        return UpdatedItemResponse
                .builder()
                .item(item)
                .build();
    }

    public ChangeVisibilityOfItemDetailsResponse getChangeVisibilityOfItemDetailsResponseFromItemAndVisibilityBo(ItemAndVisibilityBo itemAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = itemAndVisibilityBo.getVisibilityMap();
        return ChangeVisibilityOfItemDetailsResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public ItemAndVisibilityBo getItemAndVisibilityBoFromItemAndVisibilityAndPlayerBo(ItemAndVisibilityAndPlayerBo itemAndVisibilityAndPlayerBo) {
        Item item = itemAndVisibilityAndPlayerBo.getItem();
        Map<String, Visibility> visibilityMap = itemAndVisibilityAndPlayerBo.getVisibilityMap();
        return ItemAndVisibilityBo
                .builder()
                .item(item)
                .visibilityMap(visibilityMap)
                .build();
    }
}