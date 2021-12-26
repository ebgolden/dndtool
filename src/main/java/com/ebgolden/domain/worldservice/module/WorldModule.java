package domain.worldservice.module;

import com.google.inject.AbstractModule;
import domain.worldservice.GetUpdatedWorldDetails;
import domain.worldservice.GetUpdatedWorldDetailsImpl;
import domain.worldservice.ChangeVisibilityOfWorldDetails;
import domain.worldservice.ChangeVisibilityOfWorldDetailsImpl;
import domain.worldservice.bll.WorldBusinessLogic;
import domain.worldservice.bll.WorldBusinessLogicConverter;
import domain.worldservice.bll.WorldBusinessLogicConverterImpl;
import domain.worldservice.bll.WorldBusinessLogicImpl;
import domain.worldservice.dal.WorldDataAccess;
import domain.worldservice.dal.WorldDataAccessConverter;
import domain.worldservice.dal.WorldDataAccessConverterImpl;
import domain.worldservice.dal.WorldDataAccessImpl;

public class WorldModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetUpdatedWorldDetails.class).to(GetUpdatedWorldDetailsImpl.class);
        bind(ChangeVisibilityOfWorldDetails.class).to(ChangeVisibilityOfWorldDetailsImpl.class);
        bind(WorldBusinessLogicConverter.class).to(WorldBusinessLogicConverterImpl.class);
        bind(WorldBusinessLogic.class).to(WorldBusinessLogicImpl.class);
        bind(WorldDataAccessConverter.class).to(WorldDataAccessConverterImpl.class);
        bind(WorldDataAccess.class).to(WorldDataAccessImpl.class);
    }
}