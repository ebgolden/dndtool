package domain.partyservice;

import lombok.Builder;
import lombok.Data;
import common.DungeonMaster;
import common.Party;

@Builder
@Data
public class MergePartiesRequest {
    Party[] parties;
    DungeonMaster dungeonMaster;
}