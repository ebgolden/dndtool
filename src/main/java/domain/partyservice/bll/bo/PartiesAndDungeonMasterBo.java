package domain.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import common.DungeonMaster;
import common.Party;

@Builder
@Data
public class PartiesAndDungeonMasterBo {
    Party[] parties;
    DungeonMaster dungeonMaster;
}