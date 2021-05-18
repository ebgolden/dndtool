package services.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Character;
import objects.Party;
import objects.Player;

@Builder
@Data
public class PartyAndCharacterAndPlayerAndAcceptedByPartyBo {
    Party party;
    Character character;
    Player player;
    boolean acceptedByParty;
}