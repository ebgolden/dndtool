package persistence.operatorservice.bll.bo;

import common.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignBo {
    Campaign campaign;
}