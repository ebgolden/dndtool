package services.dicedetailservice;

import com.google.inject.Inject;
import services.dicedetailservice.bll.DiceDetailBusinessLogic;
import services.dicedetailservice.bll.DiceDetailBusinessLogicConverter;
import services.dicedetailservice.bll.bo.DiceAndPlayerBo;
import services.dicedetailservice.bll.bo.DiceDetailsBo;

public class GetDiceDetailsImpl implements GetDiceDetails {
    @Inject
    private DiceDetailBusinessLogicConverter diceDetailBusinessLogicConverter;
    @Inject
    private DiceDetailBusinessLogic diceDetailBusinessLogic;

    public DiceDetailsResponse getDiceDetailsResponse(DiceDetailsRequest diceDetailsRequest) {
        DiceAndPlayerBo diceAndPlayerBo = diceDetailBusinessLogicConverter.getDiceAndPlayerBoFromDiceDetailsRequest(diceDetailsRequest);
        DiceDetailsBo diceDetailsBo = diceDetailBusinessLogic.getDiceDetailsBo(diceAndPlayerBo);
        return diceDetailBusinessLogicConverter.getDiceDetailsResponseFromDiceDetailsBo(diceDetailsBo);
    }
}