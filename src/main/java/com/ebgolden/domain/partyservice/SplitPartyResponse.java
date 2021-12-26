package com.ebgolden.domain.partyservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Party;

@Builder
@Data
public class SplitPartyResponse {
    Party[] splitParties;
}