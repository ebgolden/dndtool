package com.ebgolden.domain.partyservice;

import com.google.inject.Inject;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogic;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogicConverter;
import com.ebgolden.domain.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;
import com.ebgolden.domain.partyservice.bll.bo.PartyBo;

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