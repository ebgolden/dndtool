package com.ebgolden.domain.partyservice;

import com.google.inject.Inject;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogic;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogicConverter;
import com.ebgolden.domain.partyservice.bll.bo.PartyAndSplitPartiesAndDungeonMasterBo;
import com.ebgolden.domain.partyservice.bll.bo.SplitPartiesBo;

public class SplitPartyImpl implements SplitParty {
    @Inject
    private PartyBusinessLogicConverter partyBusinessLogicConverter;
    @Inject
    private PartyBusinessLogic partyBusinessLogic;

    public SplitPartyResponse getSplitPartyResponse(SplitPartyRequest splitPartyRequest) {
        PartyAndSplitPartiesAndDungeonMasterBo partyAndSplitPartiesAndDungeonMasterBo = partyBusinessLogicConverter.getPartyAndSplitPartiesAndDungeonMasterBoFromSplitPartyRequest(splitPartyRequest);
        SplitPartiesBo splitPartiesBo = partyBusinessLogic.getSplitPartiesBo(partyAndSplitPartiesAndDungeonMasterBo);
        return partyBusinessLogicConverter.getSplitPartyResponseFromSplitPartiesBo(splitPartiesBo);
    }
}