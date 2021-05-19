package services.racedetailservice;

import com.google.inject.Inject;
import services.racedetailservice.bll.RaceDetailBusinessLogicConverter;
import services.racedetailservice.bll.RaceDetailBusinessLogic;
import services.racedetailservice.bll.bo.RaceBo;
import services.racedetailservice.bll.bo.RaceDetailsBo;

public class GetRaceDetailsImpl implements GetRaceDetails {
    @Inject
    private RaceDetailBusinessLogicConverter raceDetailBusinessLogicConverter;
    @Inject
    private RaceDetailBusinessLogic raceDetailBusinessLogic;

    public RaceDetailsResponse getRaceDetailsResponse(RaceDetailsRequest raceDetailsRequest) {
        RaceBo raceBo = raceDetailBusinessLogicConverter.getRaceBoFromRaceDetailsRequest(raceDetailsRequest);
        RaceDetailsBo raceDetailsBo = raceDetailBusinessLogic.getRaceDetailsBo(raceBo);
        return raceDetailBusinessLogicConverter.getRaceDetailsResponseFromRaceDetailsBo(raceDetailsBo);
    }
}