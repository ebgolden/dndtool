package services.partyservice.dal;

import services.partyservice.bll.bo.*;
import services.partyservice.dal.dao.*;

public interface PartyDataAccessConverter {
    PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo);

    PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerAndAcceptedByPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo);

    PartyAndSplitPartiesDao getPartyAndSplitPartiesDaoFromPartyAndSplitPartiesAndDungeonMasterBo(PartyAndSplitPartiesAndDungeonMasterBo partyAndSplitPartiesAndDungeonMasterBo);

    PartiesDao getPartiesDaoFromPartiesAndDungeonMasterBo(PartiesAndDungeonMasterBo partiesAndDungeonMasterBo);

    PartyBo getPartyBoFromPartyDao(PartyDao partyDao);

    SplitPartiesBo getSplitPartiesBoFromSplitPartiesDao(SplitPartiesDao splitPartiesDao);

    PartyDao getPartyDaoFromPartyJson(String partyJson);

    SplitPartiesDao getSplitPartiesDaoFromSplitPartiesJson(String splitPartiesJson);
}