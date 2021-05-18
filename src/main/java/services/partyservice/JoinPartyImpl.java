package services.partyservice;

import com.google.inject.Inject;
import services.partyservice.bll.PartyBusinessLogic;
import services.partyservice.bll.PartyBusinessLogicConverter;
import services.partyservice.bll.bo.JoinedPartyBo;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerAndAcceptedByPartyBo;

public class JoinPartyImpl implements JoinParty {
    @Inject
    private PartyBusinessLogicConverter partyBusinessLogicConverter;
    @Inject
    private PartyBusinessLogic partyBusinessLogic;

    public JoinPartyResponse getJoinPartyResponse(JoinPartyRequest joinPartyRequest) {
        PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo = partyBusinessLogicConverter.getPartyAndCharacterAndPlayerAndAcceptedByPartyBoFromJoinPartyRequest(joinPartyRequest);
        JoinedPartyBo joinedPartyBo = partyBusinessLogic.getJoinedPartyBo(partyAndCharacterAndPlayerAndAcceptedByPartyBo);
        return partyBusinessLogicConverter.getJoinPartyResponseFromJoinedPartyBo(joinedPartyBo);
    }
}