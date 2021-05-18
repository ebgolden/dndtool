package services.partyservice.bll;

import services.partyservice.bll.bo.JoinedPartyBo;
import services.partyservice.bll.bo.LeftPartyBo;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerAndAcceptedByPartyBo;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;

public interface PartyBusinessLogic {
    LeftPartyBo getLeftPartyBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo);

    JoinedPartyBo getJoinedPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo);
}