package persistence.operatorservice.bll.bo;

import common.DungeonMaster;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DungeonMasterBo {
    DungeonMaster dungeonMaster;
}