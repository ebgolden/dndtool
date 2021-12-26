package com.ebgolden.domain.partyservice;

import com.google.inject.Inject;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogic;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogicConverter;
import com.ebgolden.domain.partyservice.bll.bo.PartyAndCharacterAndPlayerAndAcceptedByPartyBo;
import com.ebgolden.domain.partyservice.bll.bo.PartyBo;

public class JoinPartyImpl implements JoinParty {
    @Inject
    private PartyBusinessLogicConverter partyBusinessLogicConverter;
    @Inject
    private PartyBusinessLogic partyBusinessLogic;

    public JoinPartyResponse getJoinPartyResponse(JoinPartyRequest joinPartyRequest) {
        PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo = partyBusinessLogicConverter.getPartyAndCharacterAndPlayerAndAcceptedByPartyBoFromJoinPartyRequest(joinPartyRequest);
        PartyBo partyBo = partyBusinessLogic.getPartyBo(partyAndCharacterAndPlayerAndAcceptedByPartyBo);
        return partyBusinessLogicConverter.getJoinPartyResponseFromPartyBo(partyBo);
    }
}