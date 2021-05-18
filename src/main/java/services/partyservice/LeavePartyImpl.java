package services.partyservice;

import com.google.inject.Inject;
import services.partyservice.bll.PartyBusinessLogic;
import services.partyservice.bll.PartyBusinessLogicConverter;
import services.partyservice.bll.bo.LeftPartyBo;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;

public class LeavePartyImpl implements LeaveParty {
    @Inject
    private PartyBusinessLogicConverter partyBusinessLogicConverter;
    @Inject
    private PartyBusinessLogic partyBusinessLogic;

    public LeavePartyResponse getLeavePartyResponse(LeavePartyRequest leavePartyRequest) {
        PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo = partyBusinessLogicConverter.getPartyAndCharacterAndPlayerBoFromLeavePartyRequest(leavePartyRequest);
        LeftPartyBo leftPartyBo = partyBusinessLogic.getLeftPartyBo(partyAndCharacterAndPlayerBo);
        return partyBusinessLogicConverter.getLeavePartyResponseFromLeftPartyBo(leftPartyBo);
    }
}