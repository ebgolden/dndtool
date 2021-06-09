package domain.itemservice;

import com.google.inject.Inject;
import domain.itemservice.bll.ItemBusinessLogic;
import domain.itemservice.bll.ItemBusinessLogicConverter;
import domain.itemservice.bll.bo.ItemAndVisibilityAndPlayerBo;
import domain.itemservice.bll.bo.ItemAndVisibilityBo;

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