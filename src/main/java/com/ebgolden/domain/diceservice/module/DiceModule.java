package com.ebgolden.domain.diceservice.module;

import com.google.inject.AbstractModule;
import com.ebgolden.domain.diceservice.GetUpdatedDice;
import com.ebgolden.domain.diceservice.GetUpdatedDiceImpl;
import com.ebgolden.domain.diceservice.bll.DiceBusinessLogic;
import com.ebgolden.domain.diceservice.bll.DiceBusinessLogicConverter;
import com.ebgolden.domain.diceservice.bll.DiceBusinessLogicConverterImpl;
import com.ebgolden.domain.diceservice.bll.DiceBusinessLogicImpl;
import com.ebgolden.domain.diceservice.dal.DiceDataAccess;
import com.ebgolden.domain.diceservice.dal.DiceDataAccessConverter;
import com.ebgolden.domain.diceservice.dal.DiceDataAccessConverterImpl;
import com.ebgolden.domain.diceservice.dal.DiceDataAccessImpl;

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