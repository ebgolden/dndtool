package services.dataoperatorservice.dal.dao;

import commonobjects.Campaign;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class PortCampaignMapDao {
    Map<Integer, Campaign> portCampaignMap;
}