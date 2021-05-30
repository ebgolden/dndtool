package services.diceservice.module;

import com.google.inject.AbstractModule;
import services.diceservice.GetUpdatedDice;
import services.diceservice.GetUpdatedDiceImpl;
import services.diceservice.bll.DiceBusinessLogic;
import services.diceservice.bll.DiceBusinessLogicConverter;
import services.diceservice.bll.DiceBusinessLogicConverterImpl;
import services.diceservice.bll.DiceBusinessLogicImpl;
import services.diceservice.dal.DiceDataAccess;
import services.diceservice.dal.DiceDataAccessConverter;
import services.diceservice.dal.DiceDataAccessConverterImpl;
import services.diceservice.dal.DiceDataAccessImpl;

public class DiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetUpdatedDice.class).to(GetUpdatedDiceImpl.class);
        bind(DiceBusinessLogicConverter.class).to(DiceBusinessLogicConverterImpl.class);
        bind(DiceBusinessLogic.class).to(DiceBusinessLogicImpl.class);
        bind(DiceDataAccessConverter.class).to(DiceDataAccessConverterImpl.class);
        bind(DiceDataAccess.class).to(DiceDataAccessImpl.class);
    }
}