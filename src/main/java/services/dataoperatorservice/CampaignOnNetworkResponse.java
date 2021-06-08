package services.dataoperatorservice;

import commonobjects.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignOnNetworkResponse {
    Campaign campaign;
}