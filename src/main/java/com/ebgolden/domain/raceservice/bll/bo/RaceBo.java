package com.ebgolden.domain.raceservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Race;

@Builder
@Value
public class RaceBo {
    Race race;
}