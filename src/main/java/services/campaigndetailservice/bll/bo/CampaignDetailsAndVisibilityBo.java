package services.campaigndetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class CampaignDetailsAndVisibilityBo {
    Campaign campaign;
    Map<String, Visibility> visibilityMap;
}