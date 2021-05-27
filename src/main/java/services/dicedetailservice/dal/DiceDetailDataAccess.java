package services.dicedetailservice.dal;

import services.dicedetailservice.dal.dao.DiceAndPlayerDao;
import services.dicedetailservice.dal.dao.DiceDetailsDao;

public interface DiceDetailDataAccess {
    DiceDetailsDao getDiceDetailsDao(DiceAndPlayerDao diceAndPlayerDao);
}