package com.ebgolden.domain.diceservice.dal;

import com.ebgolden.domain.diceservice.dal.dao.DiceAndPlayerDao;
import com.ebgolden.domain.diceservice.dal.dao.DiceDao;

public interface DiceDataAccess {
    DiceDao getDiceDao(DiceAndPlayerDao diceAndPlayerDao);
}