package domain.campaignservice.bll.bo;

import common.Campaign;
import common.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class CampaignAndVisibilityBo {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
}