package persistence.operatorservice;

import common.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignOnNetworkResponse {
    Campaign campaign;
}