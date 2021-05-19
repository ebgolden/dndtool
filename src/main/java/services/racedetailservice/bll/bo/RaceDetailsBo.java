package services.racedetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Race;

@Builder
@Value
public class RaceDetailsBo {
    Race race;
}