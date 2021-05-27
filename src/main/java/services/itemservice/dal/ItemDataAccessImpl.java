package services.itemservice.dal;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import commonobjects.DataOperator;
import services.itemservice.dal.dao.ItemDao;
import services.itemservice.dal.dao.ItemAndVisibilityDao;

public class ItemDataAccessImpl implements ItemDataAccess {
    @Inject
    @Named("api")
    private Object api;
    @Inject
    private DataOperator dataOperator;
    @Inject
    private ItemDataAccessConverter itemDataAccessConverter;

    public ItemAndVisibilityDao getItemAndVisibilityDao(ItemDao itemDao) {
        String itemJson = itemDao.getItemJson();
        dataOperator.sendRequestJson(api, itemJson);
        String itemAndVisibilityJson = dataOperator.getResponseJson();
        return itemDataAccessConverter.getItemAndVisibilityDaoFromItemAndVisibilityJson(itemAndVisibilityJson);
    }

    public ItemAndVisibilityDao getItemAndVisibilityDao(ItemAndVisibilityDao itemAndVisibilityDao) {
        String itemAndVisibilityJson = itemAndVisibilityDao.getItemAndVisibilityJson();
        dataOperator.sendRequestJson(api, itemAndVisibilityJson);
        itemAndVisibilityJson = dataOperator.getResponseJson();
        return itemDataAccessConverter.getItemAndVisibilityDaoFromItemAndVisibilityJson(itemAndVisibilityJson);
    }
}