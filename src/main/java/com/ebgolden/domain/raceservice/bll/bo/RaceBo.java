package domain.raceservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Race;

@Builder
@Value
public class RaceBo {
    Race race;
}