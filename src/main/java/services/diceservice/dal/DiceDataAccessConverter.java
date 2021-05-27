package services.diceservice.dal;

import services.diceservice.bll.bo.DiceAndPlayerBo;
import services.diceservice.bll.bo.DiceBo;
import services.diceservice.dal.dao.DiceAndPlayerDao;
import services.diceservice.dal.dao.DiceDao;

public interface DiceDataAccessConverter {
    DiceAndPlayerDao getDiceAndPlayerDaoFromDiceAndPlayerBo(DiceAndPlayerBo diceAndPlayerBo);

    DiceBo getDiceBoFromDiceDao(DiceDao diceDao);

    DiceDao getDiceDaoFromDiceJson(String diceJson);
}