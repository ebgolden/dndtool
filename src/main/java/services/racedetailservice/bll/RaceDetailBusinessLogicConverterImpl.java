package services.racedetailservice.bll;

import objects.Race;
import services.racedetailservice.RaceDetailsRequest;
import services.racedetailservice.RaceDetailsResponse;
import services.racedetailservice.bll.bo.RaceBo;
import services.racedetailservice.bll.bo.RaceDetailsBo;

public class RaceDetailBusinessLogicConverterImpl implements RaceDetailBusinessLogicConverter {
    public RaceBo getRaceBoFromRaceDetailsRequest(RaceDetailsRequest raceDetailsRequest) {
        Race race = raceDetailsRequest.getRace();
        return RaceBo
                .builder()
                .race(race)
                .build();
    }

    public RaceDetailsResponse getRaceDetailsResponseFromRaceDetailsBo(RaceDetailsBo raceDetailsBo) {
        Race race = raceDetailsBo.getRace();
        return RaceDetailsResponse
                .builder()
                .race(race)
                .build();
    }
}