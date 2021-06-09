package domain.itemservice.bll;

import common.Item;
import common.Player;
import common.Visibility;
import domain.itemservice.UpdatedItemRequest;
import domain.itemservice.UpdatedItemResponse;
import domain.itemservice.ChangeVisibilityOfItemDetailsRequest;
import domain.itemservice.ChangeVisibilityOfItemDetailsResponse;
import domain.itemservice.bll.bo.ItemAndPlayerBo;
import domain.itemservice.bll.bo.ItemAndVisibilityAndPlayerBo;
import domain.itemservice.bll.bo.ItemAndVisibilityBo;
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