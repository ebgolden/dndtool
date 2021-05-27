package services.campaignservice;

import commonobjects.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddPlayerToCampaignResponse {
    Campaign campaign;
}