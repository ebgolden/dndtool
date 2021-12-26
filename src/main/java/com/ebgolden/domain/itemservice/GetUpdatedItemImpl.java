package domain.itemservice;

import com.google.inject.Inject;
import domain.itemservice.bll.ItemBusinessLogic;
import domain.itemservice.bll.ItemBusinessLogicConverter;
import domain.itemservice.bll.bo.ItemAndPlayerBo;
import domain.itemservice.bll.bo.ItemAndVisibilityBo;

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