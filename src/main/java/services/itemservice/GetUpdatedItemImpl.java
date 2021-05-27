package services.itemservice;

import com.google.inject.Inject;
import services.itemservice.bll.ItemBusinessLogic;
import services.itemservice.bll.ItemBusinessLogicConverter;
import services.itemservice.bll.bo.ItemAndPlayerBo;
import services.itemservice.bll.bo.ItemAndVisibilityBo;

public class GetUpdatedItemImpl implements GetUpdatedItem {
    @Inject
    private ItemBusinessLogicConverter itemBusinessLogicConverter;
    @Inject
    private ItemBusinessLogic itemBusinessLogic;

    public UpdatedItemResponse getUpdatedItemResponse(UpdatedItemRequest updatedItemRequest) {
        ItemAndPlayerBo itemAndPlayerBo = itemBusinessLogicConverter.getItemAndPlayerBoFromUpdatedItemRequest(updatedItemRequest);
        ItemAndVisibilityBo itemAndVisibilityBo = itemBusinessLogic.getItemAndVisibilityBo(itemAndPlayerBo);
        return itemBusinessLogicConverter.getUpdatedItemResponseFromItemAndVisibilityBo(itemAndVisibilityBo);
    }
}