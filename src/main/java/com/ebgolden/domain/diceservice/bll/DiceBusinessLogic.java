package domain.diceservice.bll;

import domain.diceservice.bll.bo.DiceAndPlayerBo;
import domain.diceservice.bll.bo.DiceBo;

public interface DiceBusinessLogic {
    DiceBo getDiceBo(DiceAndPlayerBo diceAndPlayerBo);
}