package services.itemservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.itemservice.GetUpdatedItem;
import services.itemservice.GetUpdatedItemImpl;
import services.itemservice.ChangeVisibilityOfItemDetails;
import services.itemservice.ChangeVisibilityOfItemDetailsImpl;
import services.itemservice.bll.ItemBusinessLogic;
import services.itemservice.bll.ItemBusinessLogicConverter;
import services.itemservice.bll.ItemBusinessLogicConverterImpl;
import services.itemservice.bll.ItemBusinessLogicImpl;
import services.itemservice.dal.ItemDataAccess;
import services.itemservice.dal.ItemDataAccessConverter;
import services.itemservice.dal.ItemDataAccessConverterImpl;
import services.itemservice.dal.ItemDataAccessImpl;

public class ItemModule extends AbstractModule {
    private final Object API;

    public ItemModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetUpdatedItem.class).to(GetUpdatedItemImpl.class);
        bind(ChangeVisibilityOfItemDetails.class).to(ChangeVisibilityOfItemDetailsImpl.class);
        bind(ItemBusinessLogicConverter.class).to(ItemBusinessLogicConverterImpl.class);
        bind(ItemBusinessLogic.class).to(ItemBusinessLogicImpl.class);
        bind(ItemDataAccessConverter.class).to(ItemDataAccessConverterImpl.class);
        bind(ItemDataAccess.class).to(ItemDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}