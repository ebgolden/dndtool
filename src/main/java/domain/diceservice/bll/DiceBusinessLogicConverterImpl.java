package domain.diceservice.bll;

import common.Die;
import common.Player;
import domain.diceservice.UpdatedDiceResponse;
import domain.diceservice.UpdatedDiceRequest;
import domain.diceservice.bll.bo.DiceAndPlayerBo;
import domain.diceservice.bll.bo.DiceBo;

public class DiceBusinessLogicConverterImpl implements DiceBusinessLogicConverter {
    public DiceAndPlayerBo getDiceAndPlayerBoFromUpdatedDiceRequest(UpdatedDiceRequest updatedDiceRequest) {
        Die[] dice = updatedDiceRequest.getDice();
        Player player = updatedDiceRequest.getPlayer();
        return DiceAndPlayerBo
                .builder()
                .dice(dice)
                .player(player)
                .build();
    }

    public UpdatedDiceResponse getUpdatedDiceResponseFromDiceBo(DiceBo diceBo) {
        Die[] dice = diceBo.getDice();
        return UpdatedDiceResponse
                .builder()
                .dice(dice)
                .build();
    }
}