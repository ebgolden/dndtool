package services.partyservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Character;
import commonobjects.Party;
import commonobjects.Player;

@Builder
@Data
public class LeavePartyRequest {
    Party party;
    Character character;
    Player player;
}