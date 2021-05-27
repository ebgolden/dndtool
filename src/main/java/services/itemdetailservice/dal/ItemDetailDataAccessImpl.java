package services.itemdetailservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import objects.DataOperator;
import services.itemdetailservice.dal.dao.ItemDao;
import services.itemdetailservice.dal.dao.ItemDetailsAndVisibilityDao;

public class ItemDetailDataAccessImpl implements ItemDetailDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private ItemDetailDataAccessConverter itemDetailDataAccessConverter;

    public ItemDetailsAndVisibilityDao getItemDetailsAndVisibilityDao(ItemDao itemDao) {
        String itemJson = itemDao.getItemJson();
        dataOperator.sendRequestJson(api, itemJson);
        String itemDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return itemDetailDataAccessConverter.getItemDetailsAndVisibilityDaoFromItemDetailsAndVisibilityJson(itemDetailsAndVisibilityJson);
    }

    public ItemDetailsAndVisibilityDao getItemDetailsAndVisibilityDao(ItemDetailsAndVisibilityDao itemDetailsAndVisibilityDao) {
        String itemAndVisibilityJson = itemDetailsAndVisibilityDao.getItemDetailsAndVisibilityJson();
        dataOperator.sendRequestJson(api, itemAndVisibilityJson);
        String itemDetailsAndVisibilityJson = dataOperator.getResponseJson();
        return itemDetailDataAccessConverter.getItemDetailsAndVisibilityDaoFromItemDetailsAndVisibilityJson(itemDetailsAndVisibilityJson);
    }
}