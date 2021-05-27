package services.partyservice;

import com.google.inject.Inject;
import services.partyservice.bll.PartyBusinessLogic;
import services.partyservice.bll.PartyBusinessLogicConverter;
import services.partyservice.bll.bo.PartyAndSplitPartiesAndDungeonMasterBo;
import services.partyservice.bll.bo.SplitPartiesBo;

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