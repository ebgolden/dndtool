package domain.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import common.Character;
import common.Party;
import common.Player;

@Builder
@Data
public class PartyAndCharacterAndPlayerAndAcceptedByPartyBo {
    Party party;
    Character character;
    Player player;
    boolean acceptedByParty;
}