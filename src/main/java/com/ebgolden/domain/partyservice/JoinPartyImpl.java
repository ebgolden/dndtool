package domain.partyservice;

import com.google.inject.Inject;
import domain.partyservice.bll.PartyBusinessLogic;
import domain.partyservice.bll.PartyBusinessLogicConverter;
import domain.partyservice.bll.bo.PartyAndCharacterAndPlayerAndAcceptedByPartyBo;
import domain.partyservice.bll.bo.PartyBo;

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