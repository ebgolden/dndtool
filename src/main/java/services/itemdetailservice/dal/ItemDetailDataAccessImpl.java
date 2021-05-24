package services.itemdetailservice.dal;

import com.google.inject.Inject;
import objects.DataOperator;
import services.itemdetailservice.dal.dao.ItemDao;
import services.itemdetailservice.dal.dao.ItemDetailsAndVisibilityDao;

public class ItemDetailDataAccessImpl implements ItemDetailDataAccess {
    @Inject
    private ItemDetailDataAccessConverter itemDetailDataAccessConverter;
    @Inject
    private DataOperator dataOperator;

    public ItemDetailsAndVisibilityDao getItemDetailsAndVisibilityDao(ItemDao itemDao) {
        String itemJson = itemDao.getItemJson();
        dataOperator.sendRequestJson(itemJson);
        String latestJsonObject = dataOperator.getResponseJson();
        return itemDetailDataAccessConverter.getItemDetailsAndVisibilityDaoFromLatestJsonObject(latestJsonObject);
    }

    public ItemDetailsAndVisibilityDao getItemDetailsAndVisibilityDao(ItemDetailsAndVisibilityDao itemDetailsAndVisibilityDao) {
        String itemDetailsAndVisibilityJson = itemDetailsAndVisibilityDao.getItemDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(itemDetailsAndVisibilityJson);
        String updatedJsonObject = dataOperator.getResponseJson();
        return itemDetailDataAccessConverter.getItemDetailsAndVisibilityDaoFromLatestJsonObject(updatedJsonObject);
    }
}