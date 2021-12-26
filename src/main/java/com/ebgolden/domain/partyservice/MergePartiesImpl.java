package com.ebgolden.domain.partyservice;

import com.google.inject.Inject;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogic;
import com.ebgolden.domain.partyservice.bll.PartyBusinessLogicConverter;
import com.ebgolden.domain.partyservice.bll.bo.PartiesAndDungeonMasterBo;
import com.ebgolden.domain.partyservice.bll.bo.PartyBo;

public class MergePartiesImpl implements MergeParties {
    @Inject
    private PartyBusinessLogicConverter partyBusinessLogicConverter;
    @Inject
    private PartyBusinessLogic partyBusinessLogic;

    public MergePartiesResponse getMergePartiesResponse(MergePartiesRequest mergePartiesRequest) {
        PartiesAndDungeonMasterBo partiesAndDungeonMasterBo = partyBusinessLogicConverter.getPartiesAndDungeonMasterBoFromMergePartiesRequest(mergePartiesRequest);
        PartyBo partyBo = partyBusinessLogic.getPartyBo(partiesAndDungeonMasterBo);
        return partyBusinessLogicConverter.getMergePartiesResponseFromPartyBo(partyBo);
    }
}