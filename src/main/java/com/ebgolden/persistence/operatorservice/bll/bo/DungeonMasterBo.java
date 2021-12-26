package com.ebgolden.persistence.operatorservice.bll.bo;

import com.ebgolden.common.DungeonMaster;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DungeonMasterBo {
    DungeonMaster dungeonMaster;
}