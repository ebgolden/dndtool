package services.dicedetailservice.dal;

import services.dicedetailservice.bll.bo.DiceAndPlayerBo;
import services.dicedetailservice.bll.bo.DiceDetailsBo;
import services.dicedetailservice.dal.dao.DiceAndPlayerDao;
import services.dicedetailservice.dal.dao.DiceDetailsDao;

public interface DiceDetailDataAccessConverter {
    DiceAndPlayerDao getDiceAndPlayerDaoFromDiceAndPlayerBo(DiceAndPlayerBo diceAndPlayerBo);

    DiceDetailsBo getDiceDetailsBoFromDiceDetailsDao(DiceDetailsDao diceDetailsDao);

    DiceDetailsDao getDiceDetailsDaoFromDiceDetailsJson(String diceDetailsJson);
}