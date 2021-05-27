package services.dicedetailservice.bll;

import objects.Die;
import objects.Player;
import services.dicedetailservice.DiceDetailsResponse;
import services.dicedetailservice.DiceDetailsRequest;
import services.dicedetailservice.bll.bo.DiceAndPlayerBo;
import services.dicedetailservice.bll.bo.DiceDetailsBo;

public class DiceDetailBusinessLogicConverterImpl implements DiceDetailBusinessLogicConverter {
    public DiceAndPlayerBo getDiceAndPlayerBoFromDiceDetailsRequest(DiceDetailsRequest diceDetailsRequest) {
        Die[] dice = diceDetailsRequest.getDice();
        Player player = diceDetailsRequest.getPlayer();
        return DiceAndPlayerBo
                .builder()
                .dice(dice)
                .player(player)
                .build();
    }

    public DiceDetailsResponse getDiceDetailsResponseFromDiceDetailsBo(DiceDetailsBo diceDetailsBo) {
        Die[] dice = diceDetailsBo.getDice();
        return DiceDetailsResponse
                .builder()
                .dice(dice)
                .build();
    }
}