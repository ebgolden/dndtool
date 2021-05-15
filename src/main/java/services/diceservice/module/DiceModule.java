package services.diceservice.module;

import com.google.inject.AbstractModule;
import services.diceservice.AutoRollDice;
import services.diceservice.AutoRollDiceImpl;
import services.diceservice.InputDice;
import services.diceservice.InputDiceImpl;
import services.diceservice.bll.DiceBusinessLogic;
import services.diceservice.bll.DiceBusinessLogicConverter;
import services.diceservice.bll.DiceBusinessLogicConverterImpl;
import services.diceservice.bll.DiceBusinessLogicImpl;

public class DiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(InputDice.class).to(InputDiceImpl.class);
        bind(AutoRollDice.class).to(AutoRollDiceImpl.class);
        bind(DiceBusinessLogicConverter.class).to(DiceBusinessLogicConverterImpl.class);
        bind(DiceBusinessLogic.class).to(DiceBusinessLogicImpl.class);
    }
}