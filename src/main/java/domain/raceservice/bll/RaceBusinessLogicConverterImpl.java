package domain.raceservice.bll;

import common.Race;
import domain.raceservice.UpdatedRaceRequest;
import domain.raceservice.UpdatedRaceResponse;
import domain.raceservice.bll.bo.RaceBo;

public class RaceBusinessLogicConverterImpl implements RaceBusinessLogicConverter {
    public RaceBo getRaceBoFromUpdatedRaceRequest(UpdatedRaceRequest updatedRaceRequest) {
        Race race = updatedRaceRequest.getRace();
        return RaceBo
                .builder()
                .race(race)
                .build();
    }

    public UpdatedRaceResponse getUpdatedRaceResponseFromRaceBo(RaceBo raceBo) {
        Race race = raceBo.getRace();
        return UpdatedRaceResponse
                .builder()
                .race(race)
                .build();
    }
}