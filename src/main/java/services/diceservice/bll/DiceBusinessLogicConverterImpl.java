package services.diceservice.bll;

import commonobjects.Die;
import commonobjects.Player;
import services.diceservice.UpdatedDiceResponse;
import services.diceservice.UpdatedDiceRequest;
import services.diceservice.bll.bo.DiceAndPlayerBo;
import services.diceservice.bll.bo.DiceBo;

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