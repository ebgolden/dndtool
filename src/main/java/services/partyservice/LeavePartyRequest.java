package services.partyservice;

import lombok.Builder;
import lombok.Data;
import objects.Character;
import objects.Party;
import objects.Player;

@Builder
@Data
public class LeavePartyRequest {
    Party party;
    Character character;
    Player player;
}