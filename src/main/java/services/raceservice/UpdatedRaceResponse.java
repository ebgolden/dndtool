package services.raceservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Race;

@Builder
@Value
public class UpdatedRaceResponse {
    Race race;
}