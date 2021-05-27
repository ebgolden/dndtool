package services.partyservice;

import com.google.inject.Inject;
import services.partyservice.bll.PartyBusinessLogic;
import services.partyservice.bll.PartyBusinessLogicConverter;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerAndAcceptedByPartyBo;
import services.partyservice.bll.bo.PartyBo;

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