package services.itemdetailservice;

import com.google.inject.Inject;
import services.itemdetailservice.bll.ItemDetailBusinessLogic;
import services.itemdetailservice.bll.ItemDetailBusinessLogicConverter;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityAndPlayerBo;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityBo;

public class UpdateItemDetailsVisibilityImpl implements UpdateItemDetailsVisibility {
    @Inject
    private ItemDetailBusinessLogicConverter itemDetailBusinessLogicConverter;
    @Inject
    private ItemDetailBusinessLogic itemDetailBusinessLogic;

    public ItemDetailsVisibilityResponse getItemDetailsVisibilityResponse(ItemDetailsVisibilityRequest itemDetailsVisibilityRequest) {
        ItemDetailsAndVisibilityAndPlayerBo itemDetailsAndVisibilityAndPlayerBo = itemDetailBusinessLogicConverter.getItemDetailsAndVisibilityAndPlayerBoFromItemDetailsVisibilityRequest(itemDetailsVisibilityRequest);
        ItemDetailsAndVisibilityBo itemDetailsAndVisibilityBo = itemDetailBusinessLogic.getItemDetailsAndVisibilityBo(itemDetailsAndVisibilityAndPlayerBo);
        return itemDetailBusinessLogicConverter.getItemDetailsVisibilityResponseFromItemDetailsAndVisibilityBo(itemDetailsAndVisibilityBo);
    }
}