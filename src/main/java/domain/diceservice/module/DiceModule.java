package domain.diceservice.module;

import com.google.inject.AbstractModule;
import domain.diceservice.GetUpdatedDice;
import domain.diceservice.GetUpdatedDiceImpl;
import domain.diceservice.bll.DiceBusinessLogic;
import domain.diceservice.bll.DiceBusinessLogicConverter;
import domain.diceservice.bll.DiceBusinessLogicConverterImpl;
import domain.diceservice.bll.DiceBusinessLogicImpl;
import domain.diceservice.dal.DiceDataAccess;
import domain.diceservice.dal.DiceDataAccessConverter;
import domain.diceservice.dal.DiceDataAccessConverterImpl;
import domain.diceservice.dal.DiceDataAccessImpl;

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