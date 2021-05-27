package services.dicedetailservice.bll;

import services.dicedetailservice.bll.bo.DiceAndPlayerBo;
import services.dicedetailservice.bll.bo.DiceDetailsBo;

public interface DiceDetailBusinessLogic {
    DiceDetailsBo getDiceDetailsBo(DiceAndPlayerBo diceAndPlayerBo);
}