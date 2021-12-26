package com.ebgolden.domain.itemservice.dal;

import com.ebgolden.domain.itemservice.dal.dao.ItemDao;
import com.ebgolden.domain.itemservice.dal.dao.ItemAndVisibilityDao;

public interface ItemDataAccess {
    ItemAndVisibilityDao getItemAndVisibilityDao(ItemDao itemDao);

    ItemAndVisibilityDao getItemAndVisibilityDao(ItemAndVisibilityDao itemAndVisibilityDao);
}