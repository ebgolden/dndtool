package services.dicedetailservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import services.dicedetailservice.GetDiceDetails;
import services.dicedetailservice.GetDiceDetailsImpl;
import services.dicedetailservice.bll.DiceDetailBusinessLogic;
import services.dicedetailservice.bll.DiceDetailBusinessLogicConverter;
import services.dicedetailservice.bll.DiceDetailBusinessLogicConverterImpl;
import services.dicedetailservice.bll.DiceDetailBusinessLogicImpl;
import services.dicedetailservice.dal.DiceDetailDataAccess;
import services.dicedetailservice.dal.DiceDetailDataAccessConverter;
import services.dicedetailservice.dal.DiceDetailDataAccessConverterImpl;
import services.dicedetailservice.dal.DiceDetailDataAccessImpl;

public class DiceDetailModule extends AbstractModule {
    private final Object API;

    public DiceDetailModule(Object api) {
        API = api;
    }

    @Override
    protected void configure() {
        bind(GetDiceDetails.class).to(GetDiceDetailsImpl.class);
        bind(DiceDetailBusinessLogicConverter.class).to(DiceDetailBusinessLogicConverterImpl.class);
        bind(DiceDetailBusinessLogic.class).to(DiceDetailBusinessLogicImpl.class);
        bind(DiceDetailDataAccessConverter.class).to(DiceDetailDataAccessConverterImpl.class);
        bind(DiceDetailDataAccess.class).to(DiceDetailDataAccessImpl.class);
    }

    @Provides
    @Named("api")
    public Object provideAPIClass() {
        return API;
    }
}