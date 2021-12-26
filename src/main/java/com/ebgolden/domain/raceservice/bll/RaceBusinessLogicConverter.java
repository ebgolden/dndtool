package domain.raceservice.bll;

import domain.raceservice.UpdatedRaceRequest;
import domain.raceservice.UpdatedRaceResponse;
import domain.raceservice.bll.bo.RaceBo;

public interface RaceBusinessLogicConverter {
    RaceBo getRaceBoFromUpdatedRaceRequest(UpdatedRaceRequest updatedRaceRequest);

    UpdatedRaceResponse getUpdatedRaceResponseFromRaceBo(RaceBo raceBo);
}