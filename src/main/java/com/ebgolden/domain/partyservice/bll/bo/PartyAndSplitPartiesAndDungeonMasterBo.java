package com.ebgolden.domain.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Party;

@Builder
@Data
public class PartyAndSplitPartiesAndDungeonMasterBo {
    Party party;
    Party[] splitParties;
    DungeonMaster dungeonMaster;
}