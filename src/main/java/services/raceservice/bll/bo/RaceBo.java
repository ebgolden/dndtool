package services.raceservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Race;

@Builder
@Value
public class RaceBo {
    Race race;
}