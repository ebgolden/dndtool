package services.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.Character;
import commonobjects.Party;
import commonobjects.Player;

@Builder
@Data
public class PartyAndCharacterAndPlayerAndAcceptedByPartyBo {
    Party party;
    Character character;
    Player player;
    boolean acceptedByParty;
}