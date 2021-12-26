package domain.itemservice.module;

import com.google.inject.AbstractModule;
import domain.itemservice.GetUpdatedItem;
import domain.itemservice.GetUpdatedItemImpl;
import domain.itemservice.ChangeVisibilityOfItemDetails;
import domain.itemservice.ChangeVisibilityOfItemDetailsImpl;
import domain.itemservice.bll.ItemBusinessLogic;
import domain.itemservice.bll.ItemBusinessLogicConverter;
import domain.itemservice.bll.ItemBusinessLogicConverterImpl;
import domain.itemservice.bll.ItemBusinessLogicImpl;
import domain.itemservice.dal.ItemDataAccess;
import domain.itemservice.dal.ItemDataAccessConverter;
import domain.itemservice.dal.ItemDataAccessConverterImpl;
import domain.itemservice.dal.ItemDataAccessImpl;

public class ItemModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetUpdatedItem.class).to(GetUpdatedItemImpl.class);
        bind(ChangeVisibilityOfItemDetails.class).to(ChangeVisibilityOfItemDetailsImpl.class);
        bind(ItemBusinessLogicConverter.class).to(ItemBusinessLogicConverterImpl.class);
        bind(ItemBusinessLogic.class).to(ItemBusinessLogicImpl.class);
        bind(ItemDataAccessConverter.class).to(ItemDataAccessConverterImpl.class);
        bind(ItemDataAccess.class).to(ItemDataAccessImpl.class);
    }
}