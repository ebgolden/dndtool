package domain.diceservice.dal;

import domain.diceservice.bll.bo.DiceAndPlayerBo;
import domain.diceservice.bll.bo.DiceBo;
import domain.diceservice.dal.dao.DiceAndPlayerDao;
import domain.diceservice.dal.dao.DiceDao;

public interface DiceDataAccessConverter {
    DiceAndPlayerDao getDiceAndPlayerDaoFromDiceAndPlayerBo(DiceAndPlayerBo diceAndPlayerBo);

    DiceBo getDiceBoFromDiceDao(DiceDao diceDao);

    DiceDao getDiceDaoFromDiceJson(String diceJson);
}