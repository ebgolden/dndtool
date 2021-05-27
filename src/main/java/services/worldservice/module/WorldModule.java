package services.worldservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.worldservice.GetUpdatedWorldDetails;
import services.worldservice.GetUpdatedWorldDetailsImpl;
import services.worldservice.ChangeVisibilityOfWorldDetails;
import services.worldservice.ChangeVisibilityOfWorldDetailsImpl;
import services.worldservice.bll.WorldBusinessLogic;
import services.worldservice.bll.WorldBusinessLogicConverter;
import services.worldservice.bll.WorldBusinessLogicConverterImpl;
import services.worldservice.bll.WorldBusinessLogicImpl;
import services.worldservice.dal.WorldDataAccess;
import services.worldservice.dal.WorldDataAccessConverter;
import services.worldservice.dal.WorldDataAccessConverterImpl;
import services.worldservice.dal.WorldDataAccessImpl;

public class WorldModule extends AbstractModule {
    private final Object API;

    public WorldModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetUpdatedWorldDetails.class).to(GetUpdatedWorldDetailsImpl.class);
        bind(ChangeVisibilityOfWorldDetails.class).to(ChangeVisibilityOfWorldDetailsImpl.class);
        bind(WorldBusinessLogicConverter.class).to(WorldBusinessLogicConverterImpl.class);
        bind(WorldBusinessLogic.class).to(WorldBusinessLogicImpl.class);
        bind(WorldDataAccessConverter.class).to(WorldDataAccessConverterImpl.class);
        bind(WorldDataAccess.class).to(WorldDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}