package domain.campaignservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Campaign;

@Builder
@Value
public class CampaignBo {
    Campaign campaign;
}