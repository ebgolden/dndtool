package services.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.DungeonMaster;
import commonobjects.Party;

@Builder
@Data
public class PartiesAndDungeonMasterBo {
    Party[] parties;
    DungeonMaster dungeonMaster;
}