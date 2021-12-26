package com.ebgolden.domain.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Character;
import com.ebgolden.common.Party;
import com.ebgolden.common.Player;

@Builder
@Data
public class PartyAndCharacterAndPlayerAndAcceptedByPartyBo {
    Party party;
    Character character;
    Player player;
    boolean acceptedByParty;
}