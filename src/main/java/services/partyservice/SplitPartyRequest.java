package services.partyservice;

import lombok.Builder;
import lombok.Data;
import objects.DungeonMaster;
import objects.Party;

@Builder
@Data
public class SplitPartyRequest {
    Party party;
    Party[] splitParties;
    DungeonMaster dungeonMaster;
}