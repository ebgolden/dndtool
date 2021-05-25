package services.campaigndetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Visibility;
import java.util.Map;

@Builder
@Value
public class CampaignDetailsVisibilityResponse {
    Map<String, Visibility> visibilityMap;
}