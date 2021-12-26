package persistence.operatorservice.dal.dao;

import common.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignDao {
    Campaign campaign;
}