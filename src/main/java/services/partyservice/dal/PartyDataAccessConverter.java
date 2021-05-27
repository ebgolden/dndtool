package services.partyservice.dal;

import services.partyservice.bll.bo.*;
import services.partyservice.dal.dao.PartyAndCharacterDao;
import services.partyservice.dal.dao.PartyAndSplitPartiesDao;
import services.partyservice.dal.dao.PartyDao;
import services.partyservice.dal.dao.SplitPartiesDao;

public interface PartyDataAccessConverter {
    PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo);

    PartyAndCharacterDao getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerAndAcceptedByPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo);

    PartyAndSplitPartiesDao getPartyAndSplitPartiesDaoFromPartyAndSplitPartiesAndDungeonMasterBo(PartyAndSplitPartiesAndDungeonMasterBo partyAndSplitPartiesAndDungeonMasterBo);

    PartyBo getPartyBoFromPartyDao(PartyDao partyDao);

    SplitPartiesBo getSplitPartiesBoFromSplitPartiesDao(SplitPartiesDao splitPartiesDao);

    PartyDao getPartyDaoFromPartyJson(String partyJson);

    SplitPartiesDao getSplitPartiesDaoFromSplitPartiesJson(String splitPartiesJson);
}