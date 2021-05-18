package services.partyservice.bll;

import services.partyservice.JoinPartyRequest;
import services.partyservice.JoinPartyResponse;
import services.partyservice.LeavePartyRequest;
import services.partyservice.LeavePartyResponse;
import services.partyservice.bll.bo.*;

public interface PartyBusinessLogicConverter {
    PartyAndCharacterAndPlayerBo getPartyAndCharacterAndPlayerBoFromLeavePartyRequest(LeavePartyRequest leavePartyRequest);

    PartyAndCharacterAndPlayerAndAcceptedByPartyBo getPartyAndCharacterAndPlayerAndAcceptedByPartyBoFromJoinPartyRequest(JoinPartyRequest joinPartyRequest);

    LeavePartyResponse getLeavePartyResponseFromLeftPartyBo(LeftPartyBo leftPartyBo);

    JoinPartyResponse getJoinPartyResponseFromJoinedPartyBo(JoinedPartyBo joinedPartyBo);

    LeftPartyBo getLeftPartyBoFromPartyBo(PartyBo partyBo);

    JoinedPartyBo getJoinedPartyBoFromPartyBo(PartyBo partyBo);
}