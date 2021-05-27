package services.itemservice.dal;

import services.itemservice.bll.bo.ItemAndPlayerBo;
import services.itemservice.bll.bo.ItemAndVisibilityBo;
import services.itemservice.dal.dao.ItemDao;
import services.itemservice.dal.dao.ItemAndVisibilityDao;

public interface ItemDataAccessConverter {
    ItemDao getItemDaoFromItemAndPlayerBo(ItemAndPlayerBo itemAndPlayerBo);

    ItemAndVisibilityDao getItemAndVisibilityDaoFromItemAndVisibilityBo(ItemAndVisibilityBo itemAndVisibilityBo);

    ItemAndVisibilityBo getItemAndVisibilityBoFromItemAndVisibilityDao(ItemAndVisibilityDao itemAndVisibilityDao);

    ItemAndVisibilityDao getItemAndVisibilityDaoFromItemAndVisibilityJson(String itemAndVisibilityJson);
}