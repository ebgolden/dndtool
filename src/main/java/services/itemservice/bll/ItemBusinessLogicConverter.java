package services.itemservice.bll;

import services.itemservice.ChangeVisibilityOfItemDetailsRequest;
import services.itemservice.UpdatedItemRequest;
import services.itemservice.UpdatedItemResponse;
import services.itemservice.ChangeVisibilityOfItemDetailsResponse;
import services.itemservice.bll.bo.ItemAndPlayerBo;
import services.itemservice.bll.bo.ItemAndVisibilityAndPlayerBo;
import services.itemservice.bll.bo.ItemAndVisibilityBo;

public interface ItemBusinessLogicConverter {
    ItemAndPlayerBo getItemAndPlayerBoFromUpdatedItemRequest(UpdatedItemRequest updatedItemRequest);

    ItemAndVisibilityAndPlayerBo getItemAndVisibilityAndPlayerBoFromChangeVisibilityOfItemDetailsRequest(ChangeVisibilityOfItemDetailsRequest changeVisibilityOfUpdatedItemRequest);

    UpdatedItemResponse getUpdatedItemResponseFromItemAndVisibilityBo(ItemAndVisibilityBo itemAndVisibilityBo);

    ChangeVisibilityOfItemDetailsResponse getChangeVisibilityOfItemDetailsResponseFromItemAndVisibilityBo(ItemAndVisibilityBo itemAndVisibilityBo);

    ItemAndVisibilityBo getItemAndVisibilityBoFromItemAndVisibilityAndPlayerBo(ItemAndVisibilityAndPlayerBo itemAndVisibilityAndPlayerBo);
}