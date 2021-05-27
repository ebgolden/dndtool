package services.campaignservice;

import lombok.Builder;
import lombok.Value;
import commonobjects.Campaign;

@Builder
@Value
public class CreateCampaignResponse {
    Campaign campaign;
}