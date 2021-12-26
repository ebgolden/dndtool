package domain.raceservice;

import com.google.inject.Inject;
import domain.raceservice.bll.RaceBusinessLogicConverter;
import domain.raceservice.bll.RaceBusinessLogic;
import domain.raceservice.bll.bo.RaceBo;

public class GetUpdatedRaceImpl implements GetUpdatedRace {
    @Inject
    private RaceBusinessLogicConverter raceBusinessLogicConverter;
    @Inject
    private RaceBusinessLogic raceBusinessLogic;

    public UpdatedRaceResponse getUpdatedRaceResponse(UpdatedRaceRequest updatedRaceRequest) {
        RaceBo raceBo = raceBusinessLogicConverter.getRaceBoFromUpdatedRaceRequest(updatedRaceRequest);
        raceBo = raceBusinessLogic.getRaceBo(raceBo);
        return raceBusinessLogicConverter.getUpdatedRaceResponseFromRaceBo(raceBo);
    }
}