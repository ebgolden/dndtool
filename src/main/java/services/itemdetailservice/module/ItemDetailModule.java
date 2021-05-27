package services.itemdetailservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.itemdetailservice.GetItemDetails;
import services.itemdetailservice.GetItemDetailsImpl;
import services.itemdetailservice.UpdateItemDetailsVisibility;
import services.itemdetailservice.UpdateItemDetailsVisibilityImpl;
import services.itemdetailservice.bll.ItemDetailBusinessLogic;
import services.itemdetailservice.bll.ItemDetailBusinessLogicConverter;
import services.itemdetailservice.bll.ItemDetailBusinessLogicConverterImpl;
import services.itemdetailservice.bll.ItemDetailBusinessLogicImpl;
import services.itemdetailservice.dal.ItemDetailDataAccess;
import services.itemdetailservice.dal.ItemDetailDataAccessConverter;
import services.itemdetailservice.dal.ItemDetailDataAccessConverterImpl;
import services.itemdetailservice.dal.ItemDetailDataAccessImpl;

public class ItemDetailModule extends AbstractModule {
    private final Object API;

    public ItemDetailModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetItemDetails.class).to(GetItemDetailsImpl.class);
        bind(UpdateItemDetailsVisibility.class).to(UpdateItemDetailsVisibilityImpl.class);
        bind(ItemDetailBusinessLogicConverter.class).to(ItemDetailBusinessLogicConverterImpl.class);
        bind(ItemDetailBusinessLogic.class).to(ItemDetailBusinessLogicImpl.class);
        bind(ItemDetailDataAccessConverter.class).to(ItemDetailDataAccessConverterImpl.class);
        bind(ItemDetailDataAccess.class).to(ItemDetailDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}