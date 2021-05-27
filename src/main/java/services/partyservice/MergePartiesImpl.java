package services.partyservice;

import com.google.inject.Inject;
import services.partyservice.bll.PartyBusinessLogic;
import services.partyservice.bll.PartyBusinessLogicConverter;
import services.partyservice.bll.bo.PartiesAndDungeonMasterBo;
import services.partyservice.bll.bo.PartyBo;

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