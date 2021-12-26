package com.ebgolden.domain.turnqueueservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Encounter;

@Builder
@Value
public class EncounterBo {
    Encounter encounter;
}