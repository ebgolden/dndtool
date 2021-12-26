package domain.diceservice.bll;

import com.google.inject.Inject;
import common.Die;
import common.DungeonMaster;
import common.Player;
import domain.diceservice.bll.bo.DiceAndPlayerBo;
import domain.diceservice.bll.bo.DiceBo;
import domain.diceservice.dal.DiceDataAccess;
import domain.diceservice.dal.DiceDataAccessConverter;
import domain.diceservice.dal.dao.DiceAndPlayerDao;
import domain.diceservice.dal.dao.DiceDao;

public class DiceBusinessLogicImpl implements DiceBusinessLogic {
    @Inject
    private DiceDataAccessConverter diceDataAccessConverter;
    @Inject
    private DiceDataAccess diceDataAccess;

    public DiceBo getDiceBo(DiceAndPlayerBo diceAndPlayerBo) {
        DiceAndPlayerBo filteredDiceAndPlayerBo = filterDiceAndPlayerBo(diceAndPlayerBo);
        DiceAndPlayerDao diceAndPlayerDao = diceDataAccessConverter.getDiceAndPlayerDaoFromDiceAndPlayerBo(filteredDiceAndPlayerBo);
        DiceDao diceDao = diceDataAccess.getDiceDao(diceAndPlayerDao);
        return diceDataAccessConverter.getDiceBoFromDiceDao(diceDao);
    }

    private DiceAndPlayerBo filterDiceAndPlayerBo(DiceAndPlayerBo diceAndPlayerBo) {
        Die[] dice = diceAndPlayerBo.getDice();
        Player player = diceAndPlayerBo.getPlayer();
        if (player.getClass() == DungeonMaster.class)
            dice = new Die[] {};
        return DiceAndPlayerBo
                .builder()
                .dice(dice)
                .player(player)
                .build();
    }
}