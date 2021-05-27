package services.partyservice.bll;

import services.partyservice.bll.bo.*;

public interface PartyBusinessLogic {
    PartyBo getPartyBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo);

    PartyBo getPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo);

    SplitPartiesBo getSplitPartiesBo(PartyAndSplitPartiesAndDungeonMasterBo partyAndSplitPartiesAndDungeonMasterBo);

    PartyBo getPartyBo(PartiesAndDungeonMasterBo partiesAndDungeonMasterBo);
}