package services.itemservice.dal;

import services.itemservice.dal.dao.ItemDao;
import services.itemservice.dal.dao.ItemAndVisibilityDao;

public interface ItemDataAccess {
    ItemAndVisibilityDao getItemAndVisibilityDao(ItemDao itemDao);

    ItemAndVisibilityDao getItemAndVisibilityDao(ItemAndVisibilityDao itemAndVisibilityDao);
}