package services.dicedetailservice.bll;

import services.dicedetailservice.DiceDetailsRequest;
import services.dicedetailservice.DiceDetailsResponse;
import services.dicedetailservice.bll.bo.DiceAndPlayerBo;
import services.dicedetailservice.bll.bo.DiceDetailsBo;

public interface DiceDetailBusinessLogicConverter {
    DiceAndPlayerBo getDiceAndPlayerBoFromDiceDetailsRequest(DiceDetailsRequest diceDetailsRequest);

    DiceDetailsResponse getDiceDetailsResponseFromDiceDetailsBo(DiceDetailsBo diceDetailsBo);
}