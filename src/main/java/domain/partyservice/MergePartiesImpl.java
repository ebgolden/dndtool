package domain.partyservice;

import com.google.inject.Inject;
import domain.partyservice.bll.PartyBusinessLogic;
import domain.partyservice.bll.PartyBusinessLogicConverter;
import domain.partyservice.bll.bo.PartiesAndDungeonMasterBo;
import domain.partyservice.bll.bo.PartyBo;

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