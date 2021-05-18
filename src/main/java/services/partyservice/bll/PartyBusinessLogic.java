package services.partyservice.bll;

import services.partyservice.bll.bo.LeftPartyBo;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;

public interface PartyBusinessLogic {
    LeftPartyBo getLeftPartyBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo);
}