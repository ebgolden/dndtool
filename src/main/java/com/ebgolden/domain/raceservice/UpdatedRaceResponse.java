package com.ebgolden.domain.raceservice;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Race;

@Builder
@Value
public class UpdatedRaceResponse {
    Race race;
}