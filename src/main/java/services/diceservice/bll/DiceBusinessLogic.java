package services.diceservice.bll;

import services.diceservice.bll.bo.DiceAndPlayerBo;
import services.diceservice.bll.bo.DiceBo;

public interface DiceBusinessLogic {
    DiceBo getDiceBo(DiceAndPlayerBo diceAndPlayerBo);
}