package services.campaigndetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;

@Builder
@Value
public class CampaignDetailsBo {
    Campaign campaign;
}