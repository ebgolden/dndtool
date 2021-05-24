package services.itemdetailservice.bll;

import objects.Item;
import objects.Player;
import objects.Visibility;
import services.itemdetailservice.ItemDetailsRequest;
import services.itemdetailservice.ItemDetailsResponse;
import services.itemdetailservice.ItemDetailsVisibilityRequest;
import services.itemdetailservice.ItemDetailsVisibilityResponse;
import services.itemdetailservice.bll.bo.ItemAndPlayerBo;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityAndPlayerBo;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityBo;
import java.util.Map;

public class ItemDetailBusinessLogicConverterImpl implements ItemDetailBusinessLogicConverter {
    public ItemAndPlayerBo getItemAndPlayerBoFromItemDetailsRequest(ItemDetailsRequest itemDetailsRequest) {
        Item item = itemDetailsRequest.getItem();
        Player player = itemDetailsRequest.getPlayer();
        return ItemAndPlayerBo
                .builder()
                .item(item)
                .player(player)
                .build();
    }

    public ItemDetailsAndVisibilityAndPlayerBo getItemDetailsAndVisibilityAndPlayerBoFromItemDetailsVisibilityRequest(ItemDetailsVisibilityRequest itemDetailsVisibilityRequest) {
        Item item = itemDetailsVisibilityRequest.getItem();
        Map<String, Visibility> visibilityMap = itemDetailsVisibilityRequest.getVisibilityMap();
        Player player = itemDetailsVisibilityRequest.getPlayer();
        return ItemDetailsAndVisibilityAndPlayerBo
                .builder()
                .item(item)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public ItemDetailsResponse getItemDetailsResponseFromItemDetailsAndVisibilityBo(ItemDetailsAndVisibilityBo itemDetailsAndVisibilityBo) {
        Item item = itemDetailsAndVisibilityBo.getItem();
        return ItemDetailsResponse
                .builder()
                .item(item)
                .build();
    }

    public ItemDetailsVisibilityResponse getItemDetailsVisibilityResponseFromItemDetailsAndVisibilityBo(ItemDetailsAndVisibilityBo itemDetailsAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = itemDetailsAndVisibilityBo.getVisibilityMap();
        return ItemDetailsVisibilityResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public ItemDetailsAndVisibilityBo getItemDetailsAndVisibilityBoFromItemDetailsAndVisibilityAndPlayerBo(ItemDetailsAndVisibilityAndPlayerBo itemDetailsAndVisibilityAndPlayerBo) {
        Item item = itemDetailsAndVisibilityAndPlayerBo.getItem();
        Map<String, Visibility> visibilityMap = itemDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        return ItemDetailsAndVisibilityBo
                .builder()
                .item(item)
                .visibilityMap(visibilityMap)
                .build();
    }
}