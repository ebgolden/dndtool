package commonobjects;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Campaign {
    String id;
    String dungeonMasterId;
    CampaignStatus campaignStatus;
    Player[] players;
}