package services.partyservice;

import lombok.Builder;
import lombok.Data;
import objects.DungeonMaster;
import objects.Party;

@Builder
@Data
public class MergePartiesRequest {
    Party[] parties;
    DungeonMaster dungeonMaster;
}