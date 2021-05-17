package services.worlddetailservice.module;

import com.google.inject.AbstractModule;
import services.worlddetailservice.GetWorldDetails;
import services.worlddetailservice.GetWorldDetailsImpl;
import services.worlddetailservice.UpdateWorldDetailsVisibility;
import services.worlddetailservice.UpdateWorldDetailsVisibilityImpl;
import services.worlddetailservice.bll.WorldDetailBusinessLogic;
import services.worlddetailservice.bll.WorldDetailBusinessLogicConverter;
import services.worlddetailservice.bll.WorldDetailBusinessLogicConverterImpl;
import services.worlddetailservice.bll.WorldDetailBusinessLogicImpl;
import services.worlddetailservice.dal.WorldDetailDataAccess;
import services.worlddetailservice.dal.WorldDetailDataAccessConverter;
import services.worlddetailservice.dal.WorldDetailDataAccessConverterImpl;
import services.worlddetailservice.dal.WorldDetailDataAccessImpl;

public class WorldDetailModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetWorldDetails.class).to(GetWorldDetailsImpl.class);
        bind(UpdateWorldDetailsVisibility.class).to(UpdateWorldDetailsVisibilityImpl.class);
        bind(WorldDetailBusinessLogicConverter.class).to(WorldDetailBusinessLogicConverterImpl.class);
        bind(WorldDetailBusinessLogic.class).to(WorldDetailBusinessLogicImpl.class);
        bind(WorldDetailDataAccessConverter.class).to(WorldDetailDataAccessConverterImpl.class);
        bind(WorldDetailDataAccess.class).to(WorldDetailDataAccessImpl.class);
    }
}