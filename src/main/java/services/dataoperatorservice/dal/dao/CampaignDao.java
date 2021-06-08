package services.dataoperatorservice.dal.dao;

import commonobjects.Campaign;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignDao {
    Campaign campaign;
}