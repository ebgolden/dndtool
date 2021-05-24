package services.itemdetailservice.dal;

import services.itemdetailservice.bll.bo.ItemAndPlayerBo;
import services.itemdetailservice.bll.bo.ItemDetailsAndVisibilityBo;
import services.itemdetailservice.dal.dao.ItemDao;
import services.itemdetailservice.dal.dao.ItemDetailsAndVisibilityDao;

public interface ItemDetailDataAccessConverter {
    ItemDao getItemDaoFromItemAndPlayerBo(ItemAndPlayerBo itemAndPlayerBo);

    ItemDetailsAndVisibilityDao getItemDetailsAndVisibilityDaoFromItemDetailsAndVisibilityBo(ItemDetailsAndVisibilityBo itemDetailsAndVisibilityBo);

    ItemDetailsAndVisibilityBo getItemDetailsAndVisibilityBoFromItemDetailsAndVisibilityDao(ItemDetailsAndVisibilityDao itemDetailsAndVisibilityDao);

    ItemDetailsAndVisibilityDao getItemDetailsAndVisibilityDaoFromLatestJsonObject(String latestJsonObject);
}