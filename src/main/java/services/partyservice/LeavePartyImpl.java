package services.partyservice;

import com.google.inject.Inject;
import services.partyservice.bll.PartyBusinessLogic;
import services.partyservice.bll.PartyBusinessLogicConverter;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;
import services.partyservice.bll.bo.PartyBo;

public class LeavePartyImpl implements LeaveParty {
    @Inject
    private PartyBusinessLogicConverter partyBusinessLogicConverter;
    @Inject
    private PartyBusinessLogic partyBusinessLogic;

    public LeavePartyResponse getLeavePartyResponse(LeavePartyRequest leavePartyRequest) {
        PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo = partyBusinessLogicConverter.getPartyAndCharacterAndPlayerBoFromLeavePartyRequest(leavePartyRequest);
        PartyBo partyBo = partyBusinessLogic.getPartyBo(partyAndCharacterAndPlayerBo);
        return partyBusinessLogicConverter.getLeavePartyResponseFromPartyBo(partyBo);
    }
}