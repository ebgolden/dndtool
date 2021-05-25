package services.campaignservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;

@Builder
@Value
public class CampaignBo {
    Campaign campaign;
}