package services.partyservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.DungeonMaster;
import commonobjects.Party;

@Builder
@Data
public class MergePartiesRequest {
    Party[] parties;
    DungeonMaster dungeonMaster;
}