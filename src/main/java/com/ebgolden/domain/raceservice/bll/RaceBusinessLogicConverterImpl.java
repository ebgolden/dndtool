package com.ebgolden.domain.raceservice.bll;

import com.ebgolden.common.Race;
import com.ebgolden.domain.raceservice.UpdatedRaceRequest;
import com.ebgolden.domain.raceservice.UpdatedRaceResponse;
import com.ebgolden.domain.raceservice.bll.bo.RaceBo;

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