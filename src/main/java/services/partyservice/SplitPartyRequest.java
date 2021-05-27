package services.partyservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.DungeonMaster;
import commonobjects.Party;

@Builder
@Data
public class SplitPartyRequest {
    Party party;
    Party[] splitParties;
    DungeonMaster dungeonMaster;
}