package services.partyservice.bll;

import services.partyservice.LeavePartyRequest;
import services.partyservice.LeavePartyResponse;
import services.partyservice.bll.bo.LeftPartyBo;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;
import services.partyservice.bll.bo.PartyBo;

public interface PartyBusinessLogicConverter {
    PartyAndCharacterAndPlayerBo getPartyAndCharacterAndPlayerBoFromLeavePartyRequest(LeavePartyRequest leavePartyRequest);

    LeavePartyResponse getLeavePartyResponseFromLeftPartyBo(LeftPartyBo leftPartyBo);

    LeftPartyBo getLeftPartyBoFromPartyBo(PartyBo partyBo);
}