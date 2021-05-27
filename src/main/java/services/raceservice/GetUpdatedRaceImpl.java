package services.raceservice;

import com.google.inject.Inject;
import services.raceservice.bll.RaceBusinessLogicConverter;
import services.raceservice.bll.RaceBusinessLogic;
import services.raceservice.bll.bo.RaceBo;

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