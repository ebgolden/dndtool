package services.partyservice.bll;

import services.partyservice.bll.bo.*;

public interface PartyBusinessLogic {
    LeftPartyBo getLeftPartyBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo);

    JoinedPartyBo getJoinedPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo);

    SplitPartiesBo getSplitPartiesBo(PartyAndSplitPartiesAndDungeonMasterBo partyAndSplitPartiesAndDungeonMasterBo);

    PartyBo getPartyBo(PartiesAndDungeonMasterBo partiesAndDungeonMasterBo);
}