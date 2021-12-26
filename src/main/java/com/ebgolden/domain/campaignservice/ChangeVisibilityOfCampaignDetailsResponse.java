package domain.campaignservice;

import common.Visibility;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class ChangeVisibilityOfCampaignDetailsResponse {
    Map<String, Visibility> visibilityMap;
}