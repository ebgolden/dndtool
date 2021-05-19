package services.racedetailservice.bll;

import services.racedetailservice.RaceDetailsRequest;
import services.racedetailservice.RaceDetailsResponse;
import services.racedetailservice.bll.bo.RaceBo;
import services.racedetailservice.bll.bo.RaceDetailsBo;

public interface RaceDetailBusinessLogicConverter {
    RaceBo getRaceBoFromRaceDetailsRequest(RaceDetailsRequest raceDetailsRequest);

    RaceDetailsResponse getRaceDetailsResponseFromRaceDetailsBo(RaceDetailsBo raceDetailsBo);
}