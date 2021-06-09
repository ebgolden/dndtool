package domain.partyservice;

import lombok.Builder;
import lombok.Data;
import common.Character;
import common.Party;
import common.Player;

@Builder
@Data
public class JoinPartyRequest {
    Party party;
    Character character;
    Player player;
    boolean acceptedByParty;
}