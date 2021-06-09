package domain.partyservice;

import com.google.inject.Inject;
import domain.partyservice.bll.PartyBusinessLogic;
import domain.partyservice.bll.PartyBusinessLogicConverter;
import domain.partyservice.bll.bo.PartyAndSplitPartiesAndDungeonMasterBo;
import domain.partyservice.bll.bo.SplitPartiesBo;

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