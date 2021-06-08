package services.dataoperatorservice.bll.bo;

import commonobjects.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignBo {
    Campaign campaign;
}