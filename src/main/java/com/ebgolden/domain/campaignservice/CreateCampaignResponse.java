package domain.campaignservice;

import lombok.Builder;
import lombok.Value;
import common.Campaign;

@Builder
@Value
public class CreateCampaignResponse {
    Campaign campaign;
}