package com.ebgolden.domain.itemservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.itemservice.GetUpdatedItem;
import com.ebgolden.domain.itemservice.GetUpdatedItemImpl;
import com.ebgolden.domain.itemservice.ChangeVisibilityOfItemDetails;
import com.ebgolden.domain.itemservice.ChangeVisibilityOfItemDetailsImpl;
import com.ebgolden.domain.itemservice.bll.ItemBusinessLogic;
import com.ebgolden.domain.itemservice.bll.ItemBusinessLogicConverter;
import com.ebgolden.domain.itemservice.bll.ItemBusinessLogicConverterImpl;
import com.ebgolden.domain.itemservice.bll.ItemBusinessLogicImpl;
import com.ebgolden.domain.itemservice.dal.ItemDataAccess;
import com.ebgolden.domain.itemservice.dal.ItemDataAccessConverter;
import com.ebgolden.domain.itemservice.dal.ItemDataAccessConverterImpl;
import com.ebgolden.domain.itemservice.dal.ItemDataAccessImpl;

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