package com.ebgolden.domain.itemservice.dal;

import com.ebgolden.domain.itemservice.bll.bo.ItemAndPlayerBo;
import com.ebgolden.domain.itemservice.bll.bo.ItemAndVisibilityBo;
import com.ebgolden.domain.itemservice.dal.dao.ItemDao;
import com.ebgolden.domain.itemservice.dal.dao.ItemAndVisibilityDao;

public interface ItemDataAccessConverter {
    ItemDao getItemDaoFromItemAndPlayerBo(ItemAndPlayerBo itemAndPlayerBo);

    ItemAndVisibilityDao getItemAndVisibilityDaoFromItemAndVisibilityBo(ItemAndVisibilityBo itemAndVisibilityBo);

    ItemAndVisibilityBo getItemAndVisibilityBoFromItemAndVisibilityDao(ItemAndVisibilityDao itemAndVisibilityDao);

    ItemAndVisibilityDao getItemAndVisibilityDaoFromItemAndVisibilityJson(String itemAndVisibilityJson);
}