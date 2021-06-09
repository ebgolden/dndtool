package domain.partyservice.dal;

import domain.partyservice.bll.bo.*;
import domain.partyservice.dal.dao.*;

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