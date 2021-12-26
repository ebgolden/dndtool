package domain.campaignservice;

import common.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddPlayerToCampaignResponse {
    Campaign campaign;
}