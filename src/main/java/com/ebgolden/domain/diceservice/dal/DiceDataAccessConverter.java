package com.ebgolden.domain.diceservice.dal;

import com.ebgolden.domain.diceservice.bll.bo.DiceAndPlayerBo;
import com.ebgolden.domain.diceservice.bll.bo.DiceBo;
import com.ebgolden.domain.diceservice.dal.dao.DiceAndPlayerDao;
import com.ebgolden.domain.diceservice.dal.dao.DiceDao;

public interface DiceDataAccessConverter {
    DiceAndPlayerDao getDiceAndPlayerDaoFromDiceAndPlayerBo(DiceAndPlayerBo diceAndPlayerBo);

    DiceBo getDiceBoFromDiceDao(DiceDao diceDao);

    DiceDao getDiceDaoFromDiceJson(String diceJson);
}