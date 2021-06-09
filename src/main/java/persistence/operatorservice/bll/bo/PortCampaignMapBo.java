package persistence.operatorservice.bll.bo;

import common.Campaign;
import lombok.Builder;
import lombok.Value;
import java.util.Map;

@Builder
@Value
public class PortCampaignMapBo {
    Map<Integer, Campaign> portCampaignMap;
}