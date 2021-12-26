package com.ebgolden.domain.itemservice.bll;

import com.ebgolden.domain.itemservice.bll.bo.ItemAndPlayerBo;
import com.ebgolden.domain.itemservice.bll.bo.ItemAndVisibilityAndPlayerBo;
import com.ebgolden.domain.itemservice.bll.bo.ItemAndVisibilityBo;

public interface ItemBusinessLogic {
    ItemAndVisibilityBo getItemAndVisibilityBo(ItemAndPlayerBo itemAndPlayerBo);

    ItemAndVisibilityBo getItemAndVisibilityBo(ItemAndVisibilityAndPlayerBo itemAndVisibilityAndPlayerBo);
}