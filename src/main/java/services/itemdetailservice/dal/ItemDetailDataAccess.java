package services.itemdetailservice.dal;

import services.itemdetailservice.dal.dao.ItemDao;
import services.itemdetailservice.dal.dao.ItemDetailsAndVisibilityDao;

public interface ItemDetailDataAccess {
    ItemDetailsAndVisibilityDao getItemDetailsAndVisibilityDao(ItemDao itemDao);

    ItemDetailsAndVisibilityDao getItemDetailsAndVisibilityDao(ItemDetailsAndVisibilityDao itemDetailsAndVisibilityDao);
}