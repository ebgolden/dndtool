package domain.campaignservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignAndVisibilityDao {
    String campaignAndVisibilityJson;
}