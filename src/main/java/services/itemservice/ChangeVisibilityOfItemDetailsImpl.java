package services.itemservice;

import com.google.inject.Inject;
import services.itemservice.bll.ItemBusinessLogic;
import services.itemservice.bll.ItemBusinessLogicConverter;
import services.itemservice.bll.bo.ItemAndVisibilityAndPlayerBo;
import services.itemservice.bll.bo.ItemAndVisibilityBo;

public class ChangeVisibilityOfItemDetailsImpl implements ChangeVisibilityOfItemDetails {
    @Inject
    private ItemBusinessLogicConverter itemBusinessLogicConverter;
    @Inject
    private ItemBusinessLogic itemBusinessLogic;

    public ChangeVisibilityOfItemDetailsResponse getChangeVisibilityOfItemDetailsResponse(ChangeVisibilityOfItemDetailsRequest changeVisibilityOfUpdatedItemRequest) {
        ItemAndVisibilityAndPlayerBo itemAndVisibilityAndPlayerBo = itemBusinessLogicConverter.getItemAndVisibilityAndPlayerBoFromChangeVisibilityOfItemDetailsRequest(changeVisibilityOfUpdatedItemRequest);
        ItemAndVisibilityBo itemAndVisibilityBo = itemBusinessLogic.getItemAndVisibilityBo(itemAndVisibilityAndPlayerBo);
        return itemBusinessLogicConverter.getChangeVisibilityOfItemDetailsResponseFromItemAndVisibilityBo(itemAndVisibilityBo);
    }
}