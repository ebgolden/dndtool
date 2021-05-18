package services.partyservice.bll;

import objects.Character;
import objects.Party;
import objects.Player;
import services.partyservice.LeavePartyRequest;
import services.partyservice.LeavePartyResponse;
import services.partyservice.bll.bo.LeftPartyBo;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;
import services.partyservice.bll.bo.PartyBo;

public class PartyBusinessLogicConverterImpl implements PartyBusinessLogicConverter {
    public PartyAndCharacterAndPlayerBo getPartyAndCharacterAndPlayerBoFromLeavePartyRequest(LeavePartyRequest leavePartyRequest) {
        Party party = leavePartyRequest.getParty();
        Character character = leavePartyRequest.getCharacter();
        Player player = leavePartyRequest.getPlayer();
        return PartyAndCharacterAndPlayerBo
                .builder()
                .party(party)
                .character(character)
                .player(player)
                .build();
    }

    public LeavePartyResponse getLeavePartyResponseFromLeftPartyBo(LeftPartyBo leftPartyBo) {
        boolean leftParty = leftPartyBo.isLeftParty();
        return LeavePartyResponse
                .builder()
                .leftParty(leftParty)
                .build();
    }

    public LeftPartyBo getLeftPartyBoFromPartyBo(PartyBo partyBo) {
        Party party = partyBo.getParty();
        boolean leftParty = (party != null);
        return LeftPartyBo
                .builder()
                .leftParty(leftParty)
                .build();
    }
}