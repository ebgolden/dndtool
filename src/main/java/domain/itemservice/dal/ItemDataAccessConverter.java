package domain.itemservice.dal;

import domain.itemservice.bll.bo.ItemAndPlayerBo;
import domain.itemservice.bll.bo.ItemAndVisibilityBo;
import domain.itemservice.dal.dao.ItemDao;
import domain.itemservice.dal.dao.ItemAndVisibilityDao;

public interface ItemDataAccessConverter {
    ItemDao getItemDaoFromItemAndPlayerBo(ItemAndPlayerBo itemAndPlayerBo);

    ItemAndVisibilityDao getItemAndVisibilityDaoFromItemAndVisibilityBo(ItemAndVisibilityBo itemAndVisibilityBo);

    ItemAndVisibilityBo getItemAndVisibilityBoFromItemAndVisibilityDao(ItemAndVisibilityDao itemAndVisibilityDao);

    ItemAndVisibilityDao getItemAndVisibilityDaoFromItemAndVisibilityJson(String itemAndVisibilityJson);
}