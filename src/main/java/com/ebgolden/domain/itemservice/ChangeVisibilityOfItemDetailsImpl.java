package com.ebgolden.domain.itemservice;

import com.google.inject.Inject;
import com.ebgolden.domain.itemservice.bll.ItemBusinessLogic;
import com.ebgolden.domain.itemservice.bll.ItemBusinessLogicConverter;
import com.ebgolden.domain.itemservice.bll.bo.ItemAndVisibilityAndPlayerBo;
import com.ebgolden.domain.itemservice.bll.bo.ItemAndVisibilityBo;

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