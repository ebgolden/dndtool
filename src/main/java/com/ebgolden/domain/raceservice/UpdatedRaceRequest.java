package domain.raceservice;

import lombok.Builder;
import lombok.Value;
import common.Race;

@Builder
@Value
public class UpdatedRaceRequest {
    Race race;
}