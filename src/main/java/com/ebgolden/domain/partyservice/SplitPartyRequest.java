package com.ebgolden.domain.partyservice;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Party;

@Builder
@Data
public class SplitPartyRequest {
    Party party;
    Party[] splitParties;
    DungeonMaster dungeonMaster;
}