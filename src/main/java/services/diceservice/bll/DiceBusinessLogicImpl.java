package services.diceservice.bll;

import com.google.inject.Inject;
import commonobjects.Die;
import commonobjects.DungeonMaster;
import commonobjects.Player;
import services.diceservice.bll.bo.DiceAndPlayerBo;
import services.diceservice.bll.bo.DiceBo;
import services.diceservice.dal.DiceDataAccess;
import services.diceservice.dal.DiceDataAccessConverter;
import services.diceservice.dal.dao.DiceAndPlayerDao;
import services.diceservice.dal.dao.DiceDao;

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