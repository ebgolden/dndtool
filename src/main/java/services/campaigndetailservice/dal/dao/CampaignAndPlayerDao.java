package services.campaigndetailservice.dal.dao;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignAndPlayerDao {
    String campaignDetailsAndPlayerJson;
}