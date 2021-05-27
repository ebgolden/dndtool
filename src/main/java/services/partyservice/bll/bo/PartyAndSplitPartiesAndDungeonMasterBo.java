package services.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.DungeonMaster;
import commonobjects.Party;

@Builder
@Data
public class PartyAndSplitPartiesAndDungeonMasterBo {
    Party party;
    Party[] splitParties;
    DungeonMaster dungeonMaster;
}