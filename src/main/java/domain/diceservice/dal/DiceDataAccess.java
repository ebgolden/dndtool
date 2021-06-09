package domain.diceservice.dal;

import domain.diceservice.dal.dao.DiceAndPlayerDao;
import domain.diceservice.dal.dao.DiceDao;

public interface DiceDataAccess {
    DiceDao getDiceDao(DiceAndPlayerDao diceAndPlayerDao);
}