package services.dataoperatorservice.bll.bo;

import commonobjects.DungeonMaster;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DungeonMasterBo {
    DungeonMaster dungeonMaster;
}