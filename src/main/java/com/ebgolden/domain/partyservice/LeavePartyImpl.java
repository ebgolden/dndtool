package domain.partyservice;

import com.google.inject.Inject;
import domain.partyservice.bll.PartyBusinessLogic;
import domain.partyservice.bll.PartyBusinessLogicConverter;
import domain.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;
import domain.partyservice.bll.bo.PartyBo;

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