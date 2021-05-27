package services.raceservice.bll;

import commonobjects.Race;
import services.raceservice.UpdatedRaceRequest;
import services.raceservice.UpdatedRaceResponse;
import services.raceservice.bll.bo.RaceBo;

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