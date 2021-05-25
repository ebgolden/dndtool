package services.campaigndetailservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Campaign;
import objects.Player;

@Builder
@Value
public class CampaignAndPlayerBo {
    Campaign campaign;
    Player player;
}