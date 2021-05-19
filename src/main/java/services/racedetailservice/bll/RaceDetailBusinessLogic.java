package services.racedetailservice.bll;

import services.racedetailservice.bll.bo.RaceBo;
import services.racedetailservice.bll.bo.RaceDetailsBo;

public interface RaceDetailBusinessLogic {
    RaceDetailsBo getRaceDetailsBo(RaceBo raceBo);
}