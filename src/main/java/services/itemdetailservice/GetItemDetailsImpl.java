package services.itemdetailservice;

import com.google.inject.Inject;
import services.itemdetailservice.bll.ItemDetailBusinessLogic;
import services.itemdetailservice.bll.ItemDetailBusinessLogicConverter;
import services.itemdetailservice.bll.bo.ItemAndPlayerBo;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityBo;

public class GetItemDetailsImpl implements GetItemDetails {
    @Inject
    private ItemDetailBusinessLogicConverter itemDetailBusinessLogicConverter;
    @Inject
    private ItemDetailBusinessLogic itemDetailBusinessLogic;

    public ItemDetailsResponse getItemDetailsResponse(ItemDetailsRequest itemDetailsRequest) {
        ItemAndPlayerBo itemAndPlayerBo = itemDetailBusinessLogicConverter.getItemAndPlayerBoFromItemDetailsRequest(itemDetailsRequest);
        ItemDetailsAndVisibilityBo itemDetailsAndVisibilityBo = itemDetailBusinessLogic.getItemDetailsAndVisibilityBo(itemAndPlayerBo);
        return itemDetailBusinessLogicConverter.getItemDetailsResponseFromItemDetailsAndVisibilityBo(itemDetailsAndVisibilityBo);
    }
}