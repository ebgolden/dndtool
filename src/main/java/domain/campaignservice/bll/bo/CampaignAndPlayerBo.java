package domain.campaignservice.bll.bo;

import common.Campaign;
import common.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignAndPlayerBo {
    Campaign campaign;
    Player player;
}