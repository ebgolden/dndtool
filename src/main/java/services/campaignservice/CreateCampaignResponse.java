package services.campaignservice;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;

@Builder
@Value
public class CreateCampaignResponse {
    Campaign campaign;
}