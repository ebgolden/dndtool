package domain.itemservice.dal;

import domain.itemservice.dal.dao.ItemDao;
import domain.itemservice.dal.dao.ItemAndVisibilityDao;

public interface ItemDataAccess {
    ItemAndVisibilityDao getItemAndVisibilityDao(ItemDao itemDao);

    ItemAndVisibilityDao getItemAndVisibilityDao(ItemAndVisibilityDao itemAndVisibilityDao);
}