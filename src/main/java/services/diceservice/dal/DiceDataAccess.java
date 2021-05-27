package services.diceservice.dal;

import services.diceservice.dal.dao.DiceAndPlayerDao;
import services.diceservice.dal.dao.DiceDao;

public interface DiceDataAccess {
    DiceDao getDiceDao(DiceAndPlayerDao diceAndPlayerDao);
}