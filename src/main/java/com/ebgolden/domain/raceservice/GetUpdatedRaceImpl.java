package com.ebgolden.domain.raceservice;

import com.google.inject.Inject;
import com.ebgolden.domain.raceservice.bll.RaceBusinessLogicConverter;
import com.ebgolden.domain.raceservice.bll.RaceBusinessLogic;
import com.ebgolden.domain.raceservice.bll.bo.RaceBo;

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