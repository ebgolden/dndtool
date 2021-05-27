package services.dicedetailservice.bll;

import com.google.inject.Inject;
import objects.Die;
import objects.DungeonMaster;
import objects.Player;
import services.dicedetailservice.bll.bo.DiceAndPlayerBo;
import services.dicedetailservice.bll.bo.DiceDetailsBo;
import services.dicedetailservice.dal.DiceDetailDataAccess;
import services.dicedetailservice.dal.DiceDetailDataAccessConverter;
import services.dicedetailservice.dal.dao.DiceAndPlayerDao;
import services.dicedetailservice.dal.dao.DiceDetailsDao;

public class DiceDetailBusinessLogicImpl implements DiceDetailBusinessLogic {
    @Inject
    private DiceDetailDataAccessConverter diceDetailDataAccessConverter;
    @Inject
    private DiceDetailDataAccess diceDetailDataAccess;

    public DiceDetailsBo getDiceDetailsBo(DiceAndPlayerBo diceAndPlayerBo) {
        DiceAndPlayerBo filteredDiceAndPlayerBo = filterDiceAndPlayerBo(diceAndPlayerBo);
        DiceAndPlayerDao diceAndPlayerDao = diceDetailDataAccessConverter.getDiceAndPlayerDaoFromDiceAndPlayerBo(filteredDiceAndPlayerBo);
        DiceDetailsDao diceDetailsDao = diceDetailDataAccess.getDiceDetailsDao(diceAndPlayerDao);
        return diceDetailDataAccessConverter.getDiceDetailsBoFromDiceDetailsDao(diceDetailsDao);
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