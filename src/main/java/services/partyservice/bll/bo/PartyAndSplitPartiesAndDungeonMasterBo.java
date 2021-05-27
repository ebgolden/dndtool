package services.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.DungeonMaster;
import objects.Party;

@Builder
@Data
public class PartyAndSplitPartiesAndDungeonMasterBo {
    Party party;
    Party[] splitParties;
    DungeonMaster dungeonMaster;
}