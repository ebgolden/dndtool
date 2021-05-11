package diceservice.module;

import com.google.inject.AbstractModule;
import diceservice.InputDice;
import diceservice.InputDiceImpl;
import diceservice.bll.DiceBusinessLogic;
import diceservice.bll.DiceBusinessLogicConverter;
import diceservice.bll.DiceBusinessLogicConverterImpl;
import diceservice.bll.DiceBusinessLogicImpl;

public class DiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(InputDice.class).to(InputDiceImpl.class);
        bind(DiceBusinessLogicConverter.class).to(DiceBusinessLogicConverterImpl.class);
        bind(DiceBusinessLogic.class).to(DiceBusinessLogicImpl.class);
    }
}