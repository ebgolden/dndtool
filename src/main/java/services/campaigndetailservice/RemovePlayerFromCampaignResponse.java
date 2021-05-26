package services.campaigndetailservice;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;

@Builder
@Value
public class RemovePlayerFromCampaignResponse {
    Campaign campaign;
}