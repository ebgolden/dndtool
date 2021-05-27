package services.campaignservice.bll.bo;

import commonobjects.Campaign;
import commonobjects.Player;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CampaignAndPlayerBo {
    Campaign campaign;
    Player player;
}