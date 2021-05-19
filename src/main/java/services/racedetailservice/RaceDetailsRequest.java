package services.racedetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Race;

@Builder
@Value
public class RaceDetailsRequest {
    Race race;
}