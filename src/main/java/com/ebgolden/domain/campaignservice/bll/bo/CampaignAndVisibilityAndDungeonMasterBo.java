package com.ebgolden.domain.campaignservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Campaign;
import com.ebgolden.common.DungeonMaster;
import com.ebgolden.common.Visibility;
import java.util.Map;

@Builder
@Value
public class CampaignAndVisibilityAndDungeonMasterBo {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
    DungeonMaster dungeonMaster;
}