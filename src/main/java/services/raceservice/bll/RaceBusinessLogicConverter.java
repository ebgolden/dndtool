package services.raceservice.bll;

import services.raceservice.UpdatedRaceRequest;
import services.raceservice.UpdatedRaceResponse;
import services.raceservice.bll.bo.RaceBo;

public interface RaceBusinessLogicConverter {
    RaceBo getRaceBoFromUpdatedRaceRequest(UpdatedRaceRequest updatedRaceRequest);

    UpdatedRaceResponse getUpdatedRaceResponseFromRaceBo(RaceBo raceBo);
}