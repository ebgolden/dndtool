package com.ebgolden.domain.raceservice.bll;

import com.ebgolden.domain.raceservice.UpdatedRaceRequest;
import com.ebgolden.domain.raceservice.UpdatedRaceResponse;
import com.ebgolden.domain.raceservice.bll.bo.RaceBo;

public interface RaceBusinessLogicConverter {
    RaceBo getRaceBoFromUpdatedRaceRequest(UpdatedRaceRequest updatedRaceRequest);

    UpdatedRaceResponse getUpdatedRaceResponseFromRaceBo(RaceBo raceBo);
}