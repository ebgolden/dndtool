package services.campaignservice.bll.bo;

import commonobjects.Campaign;
import commonobjects.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class CampaignAndVisibilityBo {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
}