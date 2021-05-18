package services.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Character;
import objects.Party;
import objects.Player;

@Builder
@Data
public class PartyAndCharacterAndPlayerBo {
    Party party;
    Character character;
    Player player;
}